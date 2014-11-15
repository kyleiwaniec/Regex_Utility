package edu.mccc.cos210.ds;
import java.util.Iterator;
/**
 * Hash table implementation using chaining.
 **/
public class HashTable<K, V> implements HashMapInt<K, V>{

    /** The table */
    private DoublyLinkedList<Entry<K, V>>[] table;
    /** The number of keys */
    private int numKeys;
    /** The capacity */
    private static final int CAPACITY = 101;
    /** The maximum load factor */
    private static final double LOAD_THRESHOLD = 3.0;

    /** Contains key-value pairs for a hash table. */
    private static class Entry<K, V> implements MapEntryInt<K, V> {

        /** The key */
        private K key;
        /** The value */
        private V value;

        /**
         * Creates a new key-value pair.
         * @param key The key
         * @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key.
         * @return The key
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value.
         * @return The value
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * Sets the value.
         * @param val The new value
         * @return The old value
         */
        @Override
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

        public String toString(){
            String result = "";
            result += this.key +" : "+this.value;
            return result;
        }
    }

    public HashTable() {
        table = new DoublyLinkedList[CAPACITY];
    }

    public HashTable(int capcity) {
        table = new DoublyLinkedList[capcity];
    }
    public int mod(int a, int b){
        int c = a % b;
            if (c < 0){
                c += b;
            }
        return c;    
    }
    public long mod(int a, long b){
        long c = (long)a % b;
            if (c < 0){
                c += b;
            }
        return c;    
    }
    public int mod(long a, long b){
        long c = a % b;
            if (c < 0){
                c += b;
            }
        return (int) c;    
    }

    public int hashCodefn(Object key){
              String k = (String) key;
              long h = 1099511628211l;
              long len = k.length();
              for (int i = 0; i < len; i++ ){
                h = ( h * 16777619 ) ^ k.charAt(i);
              }
              return (int) h;
    }

    public int compress(Object key){
        long PRIME = 2166136261l;
        //int hash = hashCodefn(key);
        int hash = key.hashCode();
        int index =  mod(mod((127*hash+7219), PRIME), table.length);
        //int index = ( (127*key.hashCode()+7219) % PRIME) % table.length;
        //int index = key.hashCode() % table.length;
        // if (index < 0) {
        //     index += table.length;
        // }
        return index;
    }
    /**
     * Method get for class HashTable.
     * @param key The key being sought
     * @return The value associated with this key if found;
     *         otherwise, null
     */
    @Override
    public V get(Object key) {
        int index = compress(key);
        if (table[index] == null) {
            return null; // key is not in the table.
        }
        //Search the bucket at table[index] to find the key. O(n) operation, but OK if buckets are small
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.key.equals(key)) {
                return nextItem.value;
            }
        }

        // BElOW: ^^ REWRITE sans iterator
        


        // Entry<K, V> nextItem = (Entry<K, V>) table[index].getFirst();
        // for(int i = 0; i < table[index].getSize(); i++){
        //     if (nextItem.key.equals(key)) {
        //          return nextItem.value;
        //     }
        //     while(table[index].hasNext()){
        //         nextItem = (Entry<K, V>) table[index].getNext();
        //     }
        // }
        
        return null;
    }

    /**
     * Method put for class HashTable.
     * @post This key-value pair is inserted in the
     *       table and numKeys is incremented. If the key is already
     *       in the table, its value is changed to the argument
     *       value and numKeys is not changed.
     * @param key The key of item being inserted
     * @param value The value for this key
     * @return The old value associated with this key if
     *         found; otherwise, null
     */
    @Override
    public V put(K key, V value) {
        int index = compress(key);
        if (table[index] == null) {
            // Create a new linked list at table[index].
            table[index] = new DoublyLinkedList<Entry<K, V>>();
        }

        //Search the list at table[index] to find the key. NO DUPLICATE KEYS!
        for (Entry<K, V> nextItem : table[index]) {
            // If the search is successful, replace the old value.
            if (nextItem.key.equals(key)) {
                // Replace value for this key.
                V oldVal = nextItem.value;
                nextItem.setValue(value);
                return oldVal;
            }
        }


        // assert: key is not in the table, add new item.
        table[index].addFirst(new Entry<K, V>(key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length)) {
            rehash();
        }

        return null;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < table.length; i++) {
            if(table[i] != null){
                sb.append(", "+table[i].toString());
            }
        }
        if(sb.length()>=2){sb.delete(0,2);};
        return sb.toString();
    }
    public int getBuckeSize(){
        int count = 0;
        for(int i = 0; i < table.length; i++) {
            if(table[i] != null){
                count++;
            }
        }
        return count;
    }

    public int getSize(){
        return numKeys;
    }
    public int getTableLength(){
        return table.length;
    }
    /** Returns true if empty */
    public boolean isEmpty() {
        return numKeys == 0;
    }

    private void rehash() {
        DoublyLinkedList<Entry<K, V>>[] oldTable = table;
        // Create a new double-sized, empty table
        table = new DoublyLinkedList[2 * oldTable.length];
        numKeys = 0;

            // Copy table over
        for( DoublyLinkedList<Entry<K, V>> bucket : oldTable ){
            if( bucket != null ){
                for(Entry<K,V> entry : bucket){
                    put((K) entry.getKey(), (V) entry.getValue() );
                }
            }
        }
    }

    /** removes all items with given key
    *   @return null.
    */
    public V remove(Object key){
        int index = compress(key);
        Entry<K, V> nextItem = (Entry<K, V>) table[index].getFirst();
        int size = table[index].getSize();
        for(int i = 0; i < size; i++){
            System.out.println(table[index].getSize());
            if (nextItem.key.equals(key)) {
                  table[index].removeCurrent();
                  numKeys--;
            }
            while(table[index].hasNext()){
                nextItem = (Entry<K, V>) table[index].getNext();
            }

        }
        if(table[index].isEmpty()){
            table[index] = null;
        }
        return null;
    }
}
