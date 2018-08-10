package tasks.g4g;

//Given three numbers x, y and p, compute (xy) % p.
public class ModuloPower {
    // (x^y)%p
    public static int moduloPower(int base, int exp, int modulo) {
        if(exp == 0) {
            return 1;
        } else if(exp == 1) {
            return base;
        } else {
            boolean even = ((exp & 1) == 0);
            int half = exp >> 1;
            int d = moduloPower(base, half, modulo);
            d *= d;
            d %= modulo;
            if (!even) {
                d = (base * d) % modulo;
            }
            return d;
        }
    }
}
