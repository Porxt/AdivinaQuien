package logic;
//Importaciones agregadas
import java.util.Map;
import org.jpl7.Query;
import org.jpl7.Term;

public class Question {

    public static String[] questions = {
            "El sexo de la persona es ___"
    };

    public static void run(int index, String value) {
        System.out.println("Fui llamado por LA GARRA!!!");
        // TODO Logica
        
        //Inicia Prueba de conexión a prolog
        //Colocar el nombre del archivo prolog a consultar
        String c1 = "consult('pato.pl')"; 
        Query q1 = new Query(c1);
        
        System.out.println(c1+""+(q1.hasSolution()?"Si se conecto ^.^":"Ni mergas xc ")); //muestra mensaje si hay o no conexión.
       //Termina prueba de conexión
        
       //inicia prueba de consulta
       String c2 = "es_pato(X)";//Se manda a llamar el metodo de prolog
       Query q2 = new Query(c2);

       System.out.println("Primera Solucion de "+c2+" :"+q2.oneSolution());//Se extrae el primer resultado de la consulta
       
       Map<String, Term>[] todo = q2.allSolutions();
       System.out.println("Todos los patos son: "+c2);
       for( int i=0; i < todo.length; i++){
           System.out.println("(X)= "+todo[i]);
       } 
       //Termina Prueba de consulta

    }
}
