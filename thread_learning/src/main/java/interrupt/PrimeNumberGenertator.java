package interrupt;

/**
 * TODO 线程的中断
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/9 16:07
 */
public class PrimeNumberGenertator extends Thread{

    @Override
    public void run() {
        long number = 1L;
        while (true){
            if (isPrime(number)){
                System.out.printf("%d is Prime Number\n",number);
            }
            if (isInterrupted()){
                System.out.println("Thread is interrupted");
                return;
            }
            number++;
        }
    }

    private boolean isPrime(long number){
        if (number<=2){
            return true;
        }
        for (long i=2;i<number;i++){
            if ((number % i)==0){
                return false;
            }
        }
        return true;
    }
}
