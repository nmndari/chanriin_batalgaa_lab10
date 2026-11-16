package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    // ---------- Stage 2: ALL-DU-PAIRS (added on top of D1, D2) ----------

    @Test
    @DisplayName("P1 null -> IAE: attendance 31->(32a,33)")
    void nullAttendanceThrows() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(null, 1));
    }

    @Test
    @DisplayName("P2 {}, thr=-1 -> IAE: threshold 31->(32b,33)")
    void negativeThresholdThrows() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(new int[0], -1));
    }

    @Test
    @DisplayName("P3 {}, thr=0 -> 5: present 35->(50,51)")
    void emptyArrayThresholdZero() {
        assertEquals(5, DataFlowTargets.bonus(new int[0], 0));
    }

    @Test
    @DisplayName("P4 {0,0}, thr=1 -> 0: present 35->(50,53), score 49->56, best 37->(53,56)")
    void allAbsent() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {0, 0}, 1));
    }

    @Test
    @DisplayName("P5 {1,1,1,1,1,1}, thr=6 -> 8: score 51->54")
    void longStreakMeetsThreshold() {
        assertEquals(8, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1, 1}, 6));
    }

    @Test
    @DisplayName("P6 {1,1,0,1}, thr=3 -> 5: exact threshold, broken streak (streak 41->(42,38), best 43->(42,38))")
    void brokenStreakExactThreshold() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1, 1, 0, 1}, 3));
    }

    // ---------- Part C: kills mutant M2 (best = present) ----------

    @Test
    @DisplayName("K1 {1,1,1,0,1,1}, thr=1 -> 5: present=5 but longest streak=3, no streak bonus (kills M2)")
    void fivePresentDaysButStreakOnlyThree() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1, 1, 1, 0, 1, 1}, 1));
    }
}
