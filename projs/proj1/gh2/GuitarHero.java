package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    private static final int NUMBER_OF_STRINGS = 6;
    private static final double[] STRING_FREQUENCIES = {
            82.41,  // E2
            110.00, // A2
            146.83, // D3
            196.00, // G3
            246.94, // B3
            329.63  // E4
    };
    private GuitarString[] strings;

    public GuitarHero() {
        strings = new GuitarString[NUMBER_OF_STRINGS];
        for (int i = 0; i < NUMBER_OF_STRINGS; i++) {
            strings[i] = new GuitarString(STRING_FREQUENCIES[i]);
        }
    }

    public void play() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int stringIndex = -1;
                switch (key) {
                    case '1': stringIndex = 0; break; // E2
                    case '2': stringIndex = 1; break; // A2
                    case '3': stringIndex = 2; break; // D3
                    case '4': stringIndex = 3; break; // G3
                    case '5': stringIndex = 4; break; // B3
                    case '6': stringIndex = 5; break; // E4
                }
                if (stringIndex != -1) {
                    strings[stringIndex].pluck();
                }
            }

            double sample = 0.0;
            for (GuitarString string : strings) {
                sample += string.sample();
            }

            StdAudio.play(sample);

            for (GuitarString string : strings) {
                string.tic();
            }
        }
    }

    public static void main(String[] args) {
        GuitarHero guitarHero = new GuitarHero();
        guitarHero.play();
    }
}


