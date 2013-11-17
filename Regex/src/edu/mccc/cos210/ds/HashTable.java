package edu.mccc.cos210.ds;

/**
 * Hash table implementation using chaining.
 *  @author Koffman and Wolfgang
 **/
public class HashTable<K, V> implements HashMapInt<K, V> {

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
// Insert solution to programming exercise 3, section 4, chapter 7 here
        // toString method
        public String toString(){
            String result = "";
            result += this.key +" : "+this.value;
            return result;
        }
    }

    // Constructor
    public HashTable() {
        table = new DoublyLinkedList[CAPACITY];
    }

    /**
     * Method get for class HashTable.
     * @param key The key being sought
     * @return The value associated with this key if found;
     *         otherwise, null
     */
    // private int hashCode(){
    //     // TODO
    // }

    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            return null; // key is not in the table.
        }
        // Search the list at table[index] to find the key.
        // for (Entry<K, V> nextItem : table[index]) {
        //     if (nextItem.key.equals(key)) {
        //         return nextItem.value;
        //     }
        // }

        // TODO: ^^ REWRITE sans iterator
        


        Entry<K, V> nextItem = (Entry<K, V>) table[index].getFirst();
        for(int i = 0; i < table[index].getSize(); i++){
            if (nextItem.key.equals(key)) {
                 return nextItem.value;
            }
            while(table[index].hasNext()){
                nextItem = (Entry<K, V>) table[index].getNext();
            }
        }
        
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
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            // Create a new linked list at table[index].
            table[index] = new DoublyLinkedList<Entry<K, V>>();
        }

        // Search the list at table[index] to find the key.
        // for (Entry<K, V> nextItem : table[index]) {
        //     // If the search is successful, replace the old value.
        //     if (nextItem.key.equals(key)) {
        //         // Replace value for this key.
        //         V oldVal = nextItem.value;
        //         nextItem.setValue(value);
        //         return oldVal;
        //     }
        // }

        // TODO: ^^ REWRITE sans iterator

    // assert: key is not in the table, add new item.
        table[index].addFirst(new Entry<K, V>(key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length)) {
            rehash();
        }

        return null;
    }
    public void getCurrent(){
        System.out.println(table[3].getCurrent());
    }

    public String toString(){
        String res = "";
        for(int i = 0; i < table.length; i++){
            if(table[i] != null){
                res += ", "+table[i].toString();
            }
        }
        if(res.length() > 2){
            res = res.substring(2, res.length());
        }
        
        return res;
    }

// Insert solution to programming exercise 5, section 4, chapter 7 here
    // getSize() method
    public int getSize(){
        return numKeys;
    }
    /** Returns true if empty */
    public boolean isEmpty() {
        return numKeys == 0;
    }

// Insert solution to programming exercise 2, section 4, chapter 7 here
    // rehash, and remove.
    public void rehash(){

    }
    public V remove(Object key){
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        //Search the list at table[index] to find the key.
        // for (Entry<K, V> nextItem : table[index]) {
        //     // If the search is successful, remove item
        //     if (nextItem.key.equals(key)) {
        //         // remove item, decrement numKeys
                        // if table[index] is empty, set table[index] to null
        //     }
        // }

        Entry<K, V> nextItem = (Entry<K, V>) table[index].getFirst();
        for(int i = 0; i < table[index].getSize(); i++){
            if (nextItem.key.equals(key)) {
                 // Entry<K, V> previousItem = table[index].getPrevious();
                 // System.out.println("previous item: "+previousItem.toString());
                 numKeys--;
            }
            while(table[index].hasNext()){
                nextItem = (Entry<K, V>) table[index].getNext();
            }
        }




        return null;
    }


// Insert solution to programming project 7, chapter -1 here
}
