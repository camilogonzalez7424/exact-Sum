import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public final static String separator = " "; //Constant
    public static int numbBooks;
    public static int[] booksPrice;
    public static int money;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine(); //Read first line.

        do {
            if(!line.equals("")) {
                numbBooks = Integer.parseInt(line);
                booksPrice = new int[numbBooks];

                line = br.readLine(); ////Read second line.
                String[] parts = line.split(separator);

                for (int i = 0; i < numbBooks; i++) {
                    booksPrice[i] = Integer.parseInt(parts[i]);
                }
                Arrays.sort(booksPrice); //Ordenar los precios

                line = br.readLine(); //Read third line.
                money = Integer.parseInt(line);
            }
            int price1=0;
            int price2=0;
            int count=0;
            for(int i=0; i<numbBooks;i++) {

                int numb=money-booksPrice[i];
                int pos= binarySearch(numb, i);
                int temp1=0;
                int temp2=0;
                if(pos>=0) {
                    if(booksPrice[i]<booksPrice[pos]) {
                        temp1=booksPrice[i];
                        temp2=booksPrice[pos];
                    }else {
                        temp1=booksPrice[pos];
                        temp2=booksPrice[i];
                    }

                    count++;

                    if(count==1) {
                        price1=temp1;
                        price2=temp2;
                    }else if(count>1) {
                        if((temp2-temp1)<(price2-price1)) {
                            price1=temp1;
                            price2=temp2;
                        }
                    }
                }

            }
            System.out.println("Peter should buy books whose prices are "+price1+" and "+price2+".\n");
            line = br.readLine();
            line = br.readLine();

        } while (line != null);
        br.close();
    }

    public static int binarySearch(int number, int k){
        int out = -1;
        int i=0;
        int j=booksPrice.length-1;

        while(i<=j && out<0){

            int m = (i+j)/2;

            if(m!=k && booksPrice[m]==number){
                out =m;
            }else if(booksPrice[m]>number){
                j=m-1;
            }else{
                i=m+1;
            }
        }
        return out;
    }

}