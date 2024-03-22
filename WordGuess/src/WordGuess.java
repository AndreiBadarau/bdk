import java.util.Arrays;
import  java.util.Scanner;
import  java.util.Random;
public class WordGuess {

    public static char[] StringToChar(String dictionary)
    {
        String[] dict = dictionary.split(",");
        Random random = new Random();
        int j = random.nextInt(dict.length);
        String cuvant = dict[j];
        char[] c = new char[cuvant.length()];
        for(int i=0; i<cuvant.length(); i++)
        {
            c[i] = cuvant.charAt(i);
        }
        return c;
    }


    public static int countOccurence( char[] word)
    {
        Scanner scanner = new Scanner(System.in);

        char[] copie = new char[word.length];

        Arrays.fill(copie, '-');

        int k=0; // numar de incercari
        int ok=0;
        int greseala;

        System.out.println(new String(copie) + " cuvantul are " + copie.length + " litere.");

        while(ok==0)
        {
            greseala=1;
            k++;
            ok=1;
            System.out.println("Litera este ");
            char c = scanner.next().charAt(0);

            for(int i=0; i<word.length; i++)
                if(word[i] == c)
                {
                    System.out.println("A fost buna");
                    copie[i] = c;
                    System.out.println(copie);
                    greseala = 0;
                }
            if(greseala == 1) System.out.println("Nu cred");

            for (char value : copie)
                if (value == '-') {
                    ok = 0;
                    break;
                }
            if (ok == 1) System.out.println("Cuvantul a fost " + new String(word));
        }

        return k;
    }

    public static void main(String[] args)
    {
        String dictionary="onomatopee,interjectie,intersectie,banana,mar,masina,dacia,duster,mirenesc,miosteom,aghezmuit,sinusoidal,rumpere,fairplay,prostut,cd,supuitor";
        char[] nume = StringToChar(dictionary);
        int n = countOccurence(nume);
        System.out.println("Numarul de incercari a fost " + n);
    }

}