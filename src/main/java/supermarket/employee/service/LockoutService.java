package supermarket.employee.service;

import java.util.HashMap;
import java.util.Map;

public class LockoutService {
    private Map<Integer, Integer> failedLoginAttempts = new HashMap<>();
    private Map<Integer, Long> lockoutTimes = new HashMap<>();

    public void incrementFailedLoginAttempts(int empID) {
        // Increment failed login attempts for a user
        failedLoginAttempts.put(empID, failedLoginAttempts.getOrDefault(empID, 0) + 1);
    }

    public int getFailedLoginAttempts(int empID) {
        // Get the number of failed login attempts for a user
        return failedLoginAttempts.getOrDefault(empID, 0);
    }

    public void lockUser(int empID, long lockoutDurationMillis) {
        // Set a lockout time for a user
        long lockoutTime = System.currentTimeMillis() + lockoutDurationMillis;
        lockoutTimes.put(empID, lockoutTime);
    }

    public boolean isUserLocked(int empID) {
        // Check if a user is locked based on the lockout time
        Long lockoutTime = lockoutTimes.get(empID);
        if (lockoutTime != null && lockoutTime > System.currentTimeMillis()) {
            return true; // User is locked
        }
        return false; // User is not locked
    }
}

