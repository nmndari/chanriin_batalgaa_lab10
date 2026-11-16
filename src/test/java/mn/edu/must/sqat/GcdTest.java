package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** all-DU-pairs for DataFlowTargets.gcd; line numbers refer to DataFlowTargets.java. */
class GcdTest {

    @Test
    @DisplayName("T1 gcd(7,0)=7, 0 iterations: x(13->20), y(13->15F)")
    void t1_noLoop() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    @DisplayName("T2 gcd(8,4)=4, 1 iteration: x(13->16), y(13->15T,16,17), tmp(16->18), x(17->20), y(18->15F)")
    void t2_oneIteration() {
        assertEquals(4, DataFlowTargets.gcd(8, 4));
    }

    @Test
    @DisplayName("T3 gcd(12,8)=4, 2 iterations: x(17->16), y(18->15T), y(18->16), y(18->17) + all T2 pairs")
    void t3_twoIterations() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }
}
