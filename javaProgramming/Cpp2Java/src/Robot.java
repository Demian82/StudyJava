public class Robot {
    //field

    // constructor

    // method
    void WaveHand() {
        //System.out.println("Wave hand in Robot");
    }
}

class Body extends Robot {
    // field

    // constructor

    // method
    @Override
    void WaveHand() {
        System.out.println("Wave hand in Body");
    }
}

class Arm extends Body {
    // field

    // constructor

    // method

    @Override
    void WaveHand() {
        System.out.println("Wave hand in Arm");
    }
}

class Leg extends Body {
    // field

    // constructor

    // method
    void Walking() {
        System.out.println("Walknig...");
    }
}

