package phaser.exam;

import java.util.concurrent.Phaser;

/**
 * 阶段切换-考试案例
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/24 23:30
 */
public class MyPhaser extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase){
            case 0:
                return studentsArrived();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecExercise();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }

    private boolean studentsArrived(){
        System.out.println("Phaser:The exam is going to start,students are ready.");
        System.out.println("Phaser:We have "+getRegisteredParties()+"students");
        return false;
    }

    private boolean finishFirstExercise(){
        System.out.println("Phaser:All the students have finished first exercise.");
        System.out.println("Phaser:Is time to second one.");
        return false;
    }

    private boolean finishSecExercise(){
        System.out.println("Phaser:All the students have finished second exercise.");
        System.out.println("Phaser:Is time to third one.");
        return false;
    }

    private boolean finishExam(){
        System.out.println("Phaser:All the students have finished exam.");
        System.out.println("Phaser:good luck.");
        return true;
    }
}
