public class HashTable {
    // Tamanho: 2 * 25 = 50 -> número primo mais próximo:  47
    // Fator de carga: 25/47 = 0,53

    int sizeTable = 47;
    int countItems = 0;
    LinkedList[] hashTable = new LinkedList[sizeTable];

    // Mostra espaços da tabela já preenchidos
    public void showTable() {
        System.out.println("================================================");
        for(int i = 0; i < sizeTable; i++) {
            if(hashTable[i] != null){
                System.out.print("Linha " + i + ": ");
                hashTable[i].showList();      
                System.out.println();     
            }
        }
        double occupied = (float)countItems / (float)sizeTable;
        System.out.printf(countItems + " / " + sizeTable + " - %.2f", occupied);
        System.out.println();

    }
    // Funções de Dispersão: método da divisão
    public int hashFuncion(String WebAddress, int size) {
        String key = WebAddress;
        int keyIndex = 0;

        for( int i = 0; i < key.length(); i++) {
            keyIndex = (13 * (keyIndex + key.charAt(i))) % size;
        }

        return keyIndex;
    }
    // Ajusta o tamanho da tabela, transferindo os items para a nova e maior tabela
    public void adjustmentSizeTable() {
        int newSize = (int) (sizeTable * 1.5);
        LinkedList[] newHashTable = new LinkedList[newSize];
        int newCountItems = 0;

        for(int i = 0; i < sizeTable; i++) {
            if(hashTable[i] != null) {
                while(hashTable[i].head != null) {
                    WebAddress webAddress = hashTable[i].removeFirst();
                    
                    int newKey = hashFuncion(webAddress.getAddress(), newSize);
                    if(newHashTable[newKey] == null) newHashTable[newKey] = new LinkedList();
                    
                    newHashTable[newKey].insert(webAddress);
                    newCountItems = newCountItems + 1;
                }
            }
        }
        hashTable = null;
        countItems = newCountItems;
        sizeTable = newSize;
        hashTable = newHashTable;
    }

    // Inserir novo item na tabela
    public void insert(WebAddress newWebAddress) {
        int key = hashFuncion(newWebAddress.getAddress(), sizeTable);
        if(hashTable[key] == null) {
            hashTable[key] = new LinkedList();
            hashTable[key].insert(newWebAddress);
            countItems = countItems + 1;
        } else if(!hashTable[key].existingAddress(newWebAddress.address)){
            hashTable[key].insert(newWebAddress);
            countItems = countItems + 1;
        }

        //AJUSTAR COM O TAMANHO
        double occupied = (float)countItems / (float)sizeTable;
        if(occupied >= 0.55) {
            adjustmentSizeTable();
            System.out.println("\nAjustado tamanho da tabela para [" + sizeTable +"]");
        }
    }

    // Remover item da tabela
    public WebAddress remove(String addressForRemove) {
        int key = hashFuncion(addressForRemove, sizeTable);
        if(hashTable[key] != null) {
            if(hashTable[key].existingAddress(addressForRemove)) {
                countItems = countItems - 1;
                
                return hashTable[key].remove(addressForRemove);
            } else System.out.println("Endereço não encontrado!");
            
        } else System.out.println("Lista Vazia");
        
        return null;
    }

}
