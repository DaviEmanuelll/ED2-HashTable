import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class Server {
    private static Scanner scanner = new Scanner(System.in);

    HashTable hashTable = new HashTable();
    
    Timer timer = new Timer();

    TimerTask addNewsWebAddress = new TimerTask() {
        @Override
        public void run() {

            if(hashTable.countItems < 125) {
                int i=0;
                while (i<25) {
                    hashTable.insert(createWebAddresses());
                    i++;
                }

                System.out.println("Atualizado Banco de Dados com mais [25] endereços!");
            } else {
                timer.cancel();
                timer.purge();
            }
        }

    };
    
    public void start() {
        boolean run = true;
        initializeHashTable();
        timer.scheduleAtFixedRate(addNewsWebAddress, 10000, 10000);

        while(run) {
            switch (options()) {
                case "1":
                    hashTable.showTable();
                    break;
                case "2":
                    System.out.print("Pesquisar: ");
                    String address = scanner.next();

                    simulationSearch(address);
                    break;
                case "0":
                    run = false;
                    timer.cancel();
                    break;
                default:
                    break;
            }
        }
    }

    public String options() {
        System.out.println("================================================");
        System.out.println("[1] - Mostrar Tabela(HashTable)");
        System.out.println("[2] - Buscar site");
        System.out.println();
        System.out.println("[0] - Parar");
        System.out.println("================================================");

        System.out.print("R = ");
        return scanner.next();
    }

    public void initializeHashTable() {
        WebAddress[] addresses = {
            new WebAddress("www.google.com", "192.168.0.1:3000"),
            new WebAddress("www.youtube.com", "192.168.0.1:4000"),
            new WebAddress("www.instagram.com", "192.168.0.1:1000"),
            new WebAddress("www.spotify.com", "192.168.0.1:8000"),
            new WebAddress("www.ufersa.com", "192.168.0.1:4000"),
        };

        for (int j = 0; j < addresses.length; j++) {
            hashTable.insert(addresses[j]);
        }

        for(int i = 0; i < 20; i++) {
            hashTable.insert(createWebAddresses());
        }
       
    }
     // Realiza a simulação retornando o DNS do endereço informado
     public void simulationSearch(String searchedAddress) {
        WebAddress selectedWebAddress = new WebAddress();
        int key = hashTable.hashFuncion(searchedAddress, hashTable.sizeTable);

        if(hashTable.hashTable[key] != null && hashTable.hashTable[key].existingAddress(searchedAddress)) {
            selectedWebAddress = hashTable.remove(searchedAddress);

            System.out.println("O DNS do site " + selectedWebAddress.getAddress() + " é " + selectedWebAddress.getdns());
            selectedWebAddress.countFrequency += 1;

            hashTable.insert(selectedWebAddress);
        } else System.out.println("Endereço não encontrado!");
    }

    public static WebAddress createWebAddresses() {
        ThreadLocalRandom gerador = ThreadLocalRandom.current();
        //Criar endereço
        String address = "www.";
        int sizeAddress = gerador.nextInt(4, 16);
        for(int i=0; i<sizeAddress; i++) {
            address += (char) gerador.nextInt(97, 122);
        }
        address += ".com";

        //Criar DNS 192.168.0.1
        String dns = "";
        dns += gerador.nextInt(100, 999) + ".";
        dns += gerador.nextInt(100, 999) + ".";
        dns += gerador.nextInt(99) + ".";
        dns += gerador.nextInt(99) + ":";
        dns += gerador.nextInt(9) + "000";

        WebAddress webAddress = new WebAddress(address, dns);
        return webAddress;
    }
}
