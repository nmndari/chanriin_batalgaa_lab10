package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** DU testing for DataFlowTargets.bonus; line numbers refer to DataFlowTargets.java. */
class BonusTest {

    // ---------- Stage 1: ALL-DEFS (2 tests) ----------

    @Test
    @DisplayName("D1 {1,1,1,1,1,0,1}, thr=10 -> 3: present<thr, best>=5 (score 49->54->56, streak 46->41)")
    void allDefs_lowPresenceLongStreak() {
        assertEquals(3, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1, 0, 1}, 10));
    }

    @Test
    @DisplayName("D2 {1}, thr=1 -> 5: present>=thr, best<5 (score 51->56)")
    void allDefs_singleDayMeetsThreshold() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1}, 1));
    }
}
