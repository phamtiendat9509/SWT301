package tiendat.example;

public class InsuranceClaim {
    private final String claimId;
    private double amount;
    private String claimStatus; // "Pending" | "Approved" | "Rejected"

    public InsuranceClaim(String id, double claimAmount) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Claim ID cannot be null or empty");
        }
        if (claimAmount <= 0) {
            throw new IllegalArgumentException("Claim amount must be > 0");
        }
        this.claimId = id;
        this.amount = claimAmount;
        this.claimStatus = "Pending";
    }

    public String getClaimId() { return claimId; }
    public double getAmount() { return amount; }
    public String getClaimStatus() { return claimStatus; }

    public boolean processClaim(String newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        if (!"Pending".equals(claimStatus)) return false;
        if (!newStatus.equals("Approved") && !newStatus.equals("Rejected") && !newStatus.equals("Pending")) {
            throw new IllegalArgumentException("Invalid status");
        }
        this.claimStatus = newStatus;
        return true;
    }

    public double calculatePayout() {
        if ("Approved".equals(claimStatus)) {
            return amount * 0.85;
        }
        return 0.0;
    }

    public void updateClaimAmount(double newAmount) {
        if (newAmount <= 0) {
            throw new IllegalArgumentException("Amount must be > 0");
        }
        this.amount = newAmount;
    }

    @Override
    public String toString() {
        return "InsuranceClaim{" +
                "claimId='" + claimId + '\'' +
                ", amount=" + amount +
                ", claimStatus='" + claimStatus + '\'' +
                '}';
    }
}
