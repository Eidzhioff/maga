public record Student(String firstName, String lastName, String group) {
    @Override
    public String toString() {
        return String.format("%s %s %s", firstName, lastName, group);
    }
}
