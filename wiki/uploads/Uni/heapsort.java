/*
 * PrioriCoda.java
 *
 * Created on 27 ottobre 2007, 12.50
 *
 */

import java.lang.*;
import java.util.*;

/**
 *
 * @author dario
 */
class PrioriCoda {
    
    
    public PrioriCoda() {
        System.out.println("Creazione della prioricoda");
        elementi = new Vector<Integer>();
    }
    
    // Questa funzione esegue l'inserimento di un elemento nello heap
    // Il procedimento è il seguente:
    // * inserisco l'elemento in ultima posizione, i
    // * verifico che rispetti la regola 3 dello Heap, ovvero che il
    //   padre, che si trova in i/2, sia minore di esso
    // * se lo è, ok, altrimenti li swappo, e i ora punta a i/2
    // * ripeto il controllo finché non soddisfo la condizione 3
    // Il casino nasce perché i vettori sono indicizzati da 0
    // e non da 1, come sul libro...
    void inserisci(Integer elemento) {
        elementi.add(elemento);
        
        if (elementi.size() > 1) {
            Integer k, i, swap;
            
            i = elementi.size();
            k = (i / 2);
            
            while ((i > 1) && (elementi.get(i - 1) < elementi.get(k - 1))) {
                
                // Scambio i valori
                swap = elementi.get(i - 1);
                elementi.set(i - 1, elementi.get(k - 1));
                elementi.set(k - 1, swap);
                
                //figlio ora punta a suo padre
                i = k;
                
                if (i > 1) k = i / 2;
            }
        }
    }
    
    
    // Qui invece cancello il minimo elemento dello Heap, cioè il primo
    // Copio l'elemento che sta in ultima posizione e lo metto al posto del primo
    // e vado avanti finché la condizione 3 non è rispettata
    void cancellaMin() {
        Integer i, k;
        Integer valoreI, valoreK, valoreK1;
        Boolean scambio = true;
        
        if (elementi.size() > 0) {
            // metto l'ultimo elemento al primo posto
            elementi.set(0, elementi.get(elementi.size() - 1));
            elementi.remove(elementi.size() - 1);
            
            i = 0;
            
            while ((i <= ((elementi.size() / 2) - 1)) && (scambio)) {
                k = (2 * i) + 1;
                
                valoreI = elementi.get(i);
                valoreK = elementi.get(k);
                
                if (k <= (elementi.size() - 2)) {
                    valoreK1 = elementi.get(k + 1);
                    
                    if (valoreK > valoreK1) k = k + 1;
                }
                
                if (elementi.get(k) < valoreI) {
                    Integer swap;
                    swap = elementi.get(k);
                    elementi.set(k, elementi.get(i));
                    elementi.set(i, swap);
                    i = k;
                } else {
                    scambio = false;
                }
                
            }
        }
    }
    
    
    
    
    // Semplicemente mi stampa tutto lo heap, così posso controllare a mano (sigh)
    // che sia funzionante...
    void visualizza() {
        System.out.println("Stampo gli elementi della prioricoda");
        for (int conto = 0; conto < elementi.size(); conto ++) {
            System.out.println(elementi.get(conto));
        }
        
        
    }
    
    // Restituisce il minimo elemento dello heap
    Integer min() {
        if (elementi.size() > 0) return elementi.get(0);
        else return -1;
    }
    
    private Vector<Integer> elementi;
}





/**
 *
 * @author dario
 */
public class heapsort {

    
    public static void main(String[] args) {
        System.out.println("Heapsort");
        
        // Creo un vettore di elementi
        Vector<Integer> vettore = new Vector<Integer>();
        
        // Inizializzo il generatore casuale di nnnummmerrri
        Random casuale = new Random();
        
        // Creo la mia bella PrioriCoda
        // Certo che i nomi delle strutture fanno proprio pena...
        PrioriCoda Heap = new PrioriCoda();
        
        // Qui decido quanti elementi mettere nel vettore
        // 300 è il limite, e +1 lo metto per evitare 0
        
        int quantiElementi = casuale.nextInt(100) + 1;
        
        // Aggiungo un numero casuale al vettore
        // 200 è un valore scelto a caso
        for (int conto = 0; conto < quantiElementi; conto ++) {
            vettore.add(casuale.nextInt(1000));
        }
        
        // Inserisco ogni elemento del mio vettore nello Heap
        for (int conto = 0; conto < vettore.size(); conto ++) {
            // Stampo l'elemento, per pura comodità
            System.out.println("Inserisco " + vettore.get(conto));
            
            // Inserisco effettivamente l'elemento nello Heap
            Heap.inserisci(vettore.get(conto));
        }
        
        
        // Ordinamento
        System.out.println("Vettore ordinato:");
        for (int conto = 0; conto < quantiElementi; conto ++) {
            vettore.set(conto, Heap.min());

            //cancello il minimo
            Heap.cancellaMin();
        }
        
        
        // Controllo che il vettore sia ordinato
        System.out.println("Stampo il vettore ordinato:");
        
        for (int conto = 0; conto < vettore.size() - 1; conto ++) {
            int gatto = vettore.get(conto);
            int cane = vettore.get(conto + 1);
            System.out.println(vettore.get(conto));
            if (gatto > cane) {
                System.out.println("Errore alla pos " + conto + " " + (conto + 1));
                System.out.println("vettore[conto] = " + gatto);
                System.out.println("vettore[conto + 1] = " + cane);
            }
        }
        

    }
    
}
