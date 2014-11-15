import java.util.LinkedList;
import java.util.Map;

public class MyTest<K,V> {
	public static class DoublyLinkedList<E> extends LinkedList<E> {
	}
	
	private void rehash() {
		DoublyLinkedList<Map.Entry<K, V>>[] table = null ;
		DoublyLinkedList<Map.Entry<K, V>>[] oldTable = table;

		table = new DoublyLinkedList[2 * oldTable.length];
		int numKeys = 0;

		for( DoublyLinkedList<Map.Entry<K, V>> bucket : oldTable ){
			if( bucket != null ){
				for(Object entry : bucket){
					Map.Entry<K,V> e = (Map.Entry<K,V>) entry;
					//put((K) e.getKey(), (V) e.getValue() );
				}
			}
		}
	}

	private DoublyLinkedList<Map.Entry<K, V>>[] table ;

	private void rehash2() {
		DoublyLinkedList<Map.Entry<K, V>>[] oldTable = table;

		table = new DoublyLinkedList[2 * oldTable.length];
		int numKeys = 0;

		for( DoublyLinkedList<Map.Entry<K, V>> bucket : oldTable ){
			if( bucket != null ){
				for(Map.Entry<K,V> entry : bucket){
					put(entry.getKey(),entry.getValue() );
				}
			}
		}
	}

	private void put(K key, V value) { }

}