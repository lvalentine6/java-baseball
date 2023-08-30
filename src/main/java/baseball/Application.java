package baseball;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);



        while(true){

            System.out.println("Start Baseball game");

            int a = 3;
            HashSet<Integer> ran = new HashSet<Integer>();
            for (int i = 0; ran.size()<a; i++) {
                ran.add((int) (Math.random() * 9 + 1));
            }
            System.out.println(ran);
            int st_count = 0;

            while ( !(st_count == 3) ) {

                int tmp;
                try {
                    System.out.print("Insert number : ");
                    tmp = scanner.nextInt();
                    if (!(0 < tmp && tmp < 1000)) {
                        System.out.println("Insert wrong number.");
                        break;
                    }

                } catch (Exception e) {
                    throw new IllegalArgumentException("Insert wrong number");
                }


                ArrayList<Integer> input = new ArrayList<Integer>();
                input.add((int) (tmp / 100));
                input.add((int) (tmp / 10 - Math.floor(tmp / 100) * 10));
                input.add((int) (tmp - Math.floor(tmp / 10) * 10));

                Strike st = new Strike();
                Ball ba = new Ball();


                st_count = st.Strike(ran, input);
                int ba_count = 0;
                ba_count = ba.Ball(ran, input);


                print p = new print();
                p.print(ba_count, st_count);

                input.clear();

            }




            System.out.println("You correct All number");
            System.out.println("Restart 1, Quit 2");
            int quit = scanner.nextInt();
            if(quit == 1){
                continue;
            }else if(quit == 2){
                break;
            }


        }




    }
}


class Strike {
    int count =0;
    int Strike(HashSet set, ArrayList list){

        Iterator s1 = set.iterator();
        for(int i =0; s1.hasNext(); i++){
            int value = (int)s1.next();
            if( value == (int)list.get(i) ){
                count += 1;
            }
        }
        return count;
    }
}

class Ball{

    int Ball(HashSet set, ArrayList list){
        int count = 0;

        Iterator itb = list.iterator();
        for(int i=0; i<list.size();i++){
            int value = (int)list.get(i);
            if(set.contains(value)){
                count += 1;
            }
        }
        return count;
    }
}

class print{

    void print(int i, int j){
        if(i==0) {
            System.out.println("Nothing");
        }else if(j==0){
            System.out.println(i+"Ball");
        }else if(i==j){
            System.out.println(j+"Strike");
        }else{
            System.out.println((i-j) +"Ball " +j +"Strike");
        }
    }
}


