package paul.games.heroes.level.generator.noise;

public class NoiseMap {
    private double period;
    private int width;
    private int height;
    private int off_x;
    private int off_y;
    private double freq;

    public NoiseMap(double period, int width, int height) {
        this.period = period;
        this.width = width;
        this.height = height;
        this.off_x = width * 2;
        this.off_y = width * 2;
        this.freq = 1.0 / this.period;
    }

    public double[][] generate(long seed) {
        OpenSimplex2S noise = new OpenSimplex2S(seed);
        OpenSimplex2S.GenerateContext2D noiseBulk = new OpenSimplex2S.GenerateContext2D(OpenSimplex2S.LatticeOrientation2D.Standard, this.freq, this.freq, 1.0);

        double[][] buffer = new double[height][width];
        noise.generate2(noiseBulk, buffer, this.off_x, this.off_y);
        return buffer;
    }
}
