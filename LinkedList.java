public class LinkedList {
    WebAddress head;
    WebAddress tail;

    // Mostrar Lista em ordem
    public void showList() {
        WebAddress showList = head;

        if(showList == null) {
            System.out.println("Lista vazia");
        } else {
            while (showList != null) {
                System.out.print(showList.address+" | ");
                showList = showList.next;
            }
        }
    }
    // Compara se a frequencia é maior do que a posição acima/antes
    public Boolean isBiggerThanAbove(WebAddress checkedWebcAddress) {
        if(
            checkedWebcAddress != head && 
            checkedWebcAddress.countFrequency > checkedWebcAddress.previous.countFrequency
        ) {
            return true;
        }
        return false;
    }
    // Trocar com a posição acima na lista
    public void riseUp(WebAddress goUpWebAddress) {
        if( goUpWebAddress != head) {
            if(goUpWebAddress.previous == head) {
                WebAddress goDownWebAddress = head;
                WebAddress next = goUpWebAddress.next;
    
                goUpWebAddress.previous = null;
                goUpWebAddress.next = goDownWebAddress;
                
                goDownWebAddress.previous = goUpWebAddress;
                goDownWebAddress.next = next;
                
                if(next != null) {
                    next.previous = goDownWebAddress;
                };
                
                head = goUpWebAddress;
            } else if(goUpWebAddress == tail) {
                WebAddress goDownWebAddress = goUpWebAddress.previous;
                WebAddress previous = goDownWebAddress.previous;
    
                if(previous != null) {
                    previous.next = goUpWebAddress;
                }
    
                goUpWebAddress.previous = previous;
                goUpWebAddress.next = goDownWebAddress;
    
                goDownWebAddress.previous = goUpWebAddress;
                goDownWebAddress.next = null;
    
                tail = goDownWebAddress;
            } else {
                WebAddress goDownWebAddress = goUpWebAddress.previous;
                WebAddress next = goUpWebAddress.next;
                WebAddress previous = goDownWebAddress.previous;
    
                if(previous != null) {
                    previous.next = goUpWebAddress;
                }
    
                goUpWebAddress.previous = previous;
                goUpWebAddress.next = goDownWebAddress;
    
                goDownWebAddress.previous = goUpWebAddress;
                goDownWebAddress.next = next;
    
                if(next != null) {
                    next.previous = goDownWebAddress;
                };
            }
        }
    }
    // Verifica se item já existe
    public boolean existingAddress(String searchAddress) {
        if(search(searchAddress) != null){
            return true;
        }
        return false;
    }
    // Busca de acordo com endereço
    public WebAddress search(String searchAddress) {
        WebAddress findWebAddress = head;

        while(findWebAddress != null) {
            if(findWebAddress.address.equals(searchAddress)) {
                return findWebAddress;
            }
            findWebAddress = findWebAddress.next;
        }
        
        return null;
    }
    
    // Insere novo item na lista, organizando com base na contagem de frequencia (Insert Short)
    public void insert(WebAddress newWebAddress) {
        if(!existingAddress(newWebAddress.getAddress())){
            if(head == null) {
                head = newWebAddress;
                tail = newWebAddress;
            } else {
                tail.next = newWebAddress;
                newWebAddress.previous = tail;
                newWebAddress.next = null;
                tail = newWebAddress;
            }
            // System.out.println("Adicionado: " + newWebAddress.getAddress());
            while(newWebAddress.previous != null  && isBiggerThanAbove(newWebAddress)){
                riseUp(newWebAddress);
            }
        } else System.out.println("Endereço \"" + newWebAddress.getAddress() + "\" já existente");
    }
   
    // Remove o primeiro item
    public WebAddress removeFirst() {
        WebAddress removed = null;
        if(head == tail && head != null) {
            removed = head;
            head = null;
            tail = null;
            // System.out.println("Removido: " + removed.getAddress());
            // System.out.println("Lista apagada!");
        } else if(head != null) {
            removed = head;
            head = head.next;
            head.previous = null;
            removed.previous = null;
            removed.next = null;
            // System.out.println("Removido: " + removed.getAddress());
        } else System.out.println("Lista Vazia");
        
        return removed;
    }
    // Remove o ultimo item
    public WebAddress removeLast() {
        WebAddress removed = null;
        if(head == tail && head != null) {
            removed = tail;
            head = null;
            tail = null;
            // System.out.println("Removido: " + removed.getAddress());
            // System.out.println("Lista apagada!");
        } else if(tail != null) {
            removed = tail;
            tail = tail.previous;
            tail.next = null;
            // System.out.println("Removido: " + removed.getAddress());
        } else System.out.println("Lista Vazia");
        
        return removed;
    }
    // Remover item
    public WebAddress remove(String addressForRemove) {
        WebAddress removed = null;
        if(head != null && existingAddress(addressForRemove)) {
            if (head == tail && head.address.equals(addressForRemove)) {
                removed = head;
                head = null;
                tail = null;
                // System.out.println("Lista apagada!");
            } else {
                removed = search(addressForRemove);
                if(removed == head) {
                    removeFirst();
                } else if(removed == tail) {
                    removeLast();
                } else {
                    if(removed.previous != null) {
                        removed.previous.next = removed.next;
                    }
                    if(removed.next != null) {
                        removed.next.previous = removed.previous;
                    }
                }
                // System.out.println("Removido: " + removed.getAddress());
            }
        } else System.out.println("Lista Vazia");
        return removed;
    }
}