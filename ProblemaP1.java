import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// Federico Pungo Castro - 201822943
// Luis Alfredo Castelblanco - 201910966 
public class ProblemaP1 {
    

    public static int calcularMovimientosOptimizados(int numTorres, int[] torres) {
        int totalMovimientos = 0;

        // casos base en los que no existe un arreglo y en los que es un arreglo con una sola torre
        if(numTorres <= 1 || ordenadoDeMayorAMenor(torres)){
            return totalMovimientos;
        }
    
        // Realizar movimientos entre torres adyacentes para intentar ordenarlas.
        for (int i = 0  ; i < numTorres-1; i++) {

            if(i==0 && torres[i] < torres[i + 1]){
                while (torres[i] < torres[i + 1]) {
                
                    torres[i]++;
                    torres[i + 1]--;
                    totalMovimientos++;
                   
            }}
            
            int difAnt,difSig;
            if(i>0){
                difAnt = torres[i - 1] - torres[i];
                difSig = torres[i+1] - torres[i];

                if(difAnt>difSig && torres[i] < torres[i + 1]){
                    while (torres[i] < torres[i + 1]) {
                    
                        torres[i]++;
                        torres[i - 1]--;
                        totalMovimientos++;
                       
                    }
                }
    
                else if (difSig>difAnt && torres[i] < torres[i + 1]){
                    while (torres[i] < torres[i + 1]) {
                    
                        torres[i]++;
                        torres[i + 1]--;
                        totalMovimientos++;
                       
                    }
                }
                else if(difAnt == difSig && torres[i] < torres[i + 1]){
                    while (torres[i] < torres[i + 1]) {
                    
                        torres[i]++;
                        torres[i + 1]--;
                        totalMovimientos++;
                       
                    }
                }
            }
        }
        
        if (!ordenadoDeMayorAMenor(torres)) {

            totalMovimientos += calcularMovimientosOptimizados(numTorres, torres);
        }
    
        return totalMovimientos;
    }

    public static int calcularMovimientosOptimizados2(int numTorres, int[] torres) {
        int totalMovimientos = 0;
        // casos base en los que no existe un arreglo y en los que es un arreglo con una sola torre
        if(numTorres <= 1 || ordenadoDeMayorAMenor(torres)){
            return totalMovimientos;
        }
    
        // Realizar movimientos entre torres adyacentes para intentar ordenarlas.
        for (int i = 0; i < numTorres - 1; i++) {
            while (torres[i] < torres[i + 1]) {
                torres[i]++;
                torres[i + 1]--;
                totalMovimientos++;
            }
            //System.out.println( "Arreglo torres ordenado : " + Arrays.toString(torres));
        }
        if (!ordenadoDeMayorAMenor(torres)) {
            totalMovimientos += calcularMovimientosOptimizados2(numTorres, torres);
        }
    
        return totalMovimientos;
    }

    // Metodo check para determinar si el arreglo ya esta ordenado de mayor a menor como se desea 
    public static boolean ordenadoDeMayorAMenor(int[] torres) {
        for (int i = 0; i < torres.length - 1; i++) {
            if (torres[i] < torres[i + 1]) {
                return false;
            }
        }
        return true;
    }

    //Metodo para modificar el arreglo inicial y obtener solo los valores de fichas por cada torre, eliminando
    // el valor en la posición 0 ya que este es solo el número que nos indica el número total de torres
    public static int[] eliminarPrimero(int[] original) {
        if (original == null || original.length <= 1) {
            return new int[0];
        }
    
        int[] nuevoArreglo = new int[original.length - 1];
        System.arraycopy(original, 1, nuevoArreglo, 0, nuevoArreglo.length);
    
        return nuevoArreglo;
    }

       public static void main(String[] args) throws Exception {
		
        try ( 
            InputStreamReader is= new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
        ) { 
            String line = br.readLine();
            int casos = Integer.parseInt(line);
            System.out.println("numero casos" + casos);
            line = br.readLine();
            for(int i=0;i<casos && line!=null && line.length()>0;i++) {
                final String [] dataStr = line.split(" ");
                final int[] fichas = Arrays.stream(dataStr).mapToInt(f->Integer.parseInt(f)).toArray();
                final int[] resultado = eliminarPrimero(fichas);
                final int[] resultado2 = eliminarPrimero(fichas);
                int opcion1 = calcularMovimientosOptimizados(fichas[0],resultado);
                int opcion2 = calcularMovimientosOptimizados2(fichas[0],resultado2);
                if(opcion1<opcion2){
                    System.out.println( "Valores de entrada: " +resultado.length +  ",    Movimientos necesarios: " + opcion1);
                
                    //System.out.println( "Arreglo torres ordenado : " + Arrays.toString(resultado));
                }
                else{
                    System.out.println( "Valores de entrada: " +resultado.length +  ",    Movimientos necesarios: " + opcion2);
                
                   // System.out.println( "Arreglo torres ordenado : " + Arrays.toString(resultado));
                }
                  
            line = br.readLine();
            
        }
    }
}

}
