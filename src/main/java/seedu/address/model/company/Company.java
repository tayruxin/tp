package seedu.address.model.company;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Company in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Company {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Role role;
    private final Deadline deadline;
    private final ApplicationStatus status;
    private final RecruiterName recruiterName;
    private final Priority priority;


    /**
     * Every field must be present and not null.
     */
    public Company(Name name, Phone phone, Email email, Role role, Deadline deadline, ApplicationStatus status,
                   RecruiterName recruiterName, Priority priority) {
        requireAllNonNull(name, phone, email, role, deadline, status, priority);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.deadline = deadline;
        this.status = status;
        this.recruiterName = recruiterName;
        this.priority = priority;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public RecruiterName getRecruiterName() {
        return recruiterName;
    }

    public Priority getPriority() {
        return priority;
    }

    /**
     * Returns true if both entries have the same company name and the
     * role is the same.
     */
    public boolean isSameCompany(Company otherCompany) {
        if (otherCompany == this) {
            return true;
        }

        if (otherCompany == null) {
            return false;
        }

        return otherCompany.getName() != null
                && otherCompany.getName().equals(getName())
                && otherCompany.getRole() != null
                && otherCompany.getRole().equals(getRole());
    }

    /**
     * Returns true if both companies have the same identity and data fields.
     * This defines a stronger notion of equality between two companies.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Company)) {
            return false;
        }

        Company otherCompany = (Company) other;
        return name.equals(otherCompany.name)
                && phone.equals(otherCompany.phone)
                && email.equals(otherCompany.email)
                && role.equals(otherCompany.role)
                && deadline.equals(otherCompany.deadline)
                && status.equals(otherCompany.status)
                && recruiterName.equals(otherCompany.recruiterName)
                && priority.equals(otherCompany.priority);
    }
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, role, deadline, status, recruiterName, priority);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("role", role)
                .add("status", status)
                .add("deadline", deadline)
                .add("recruiterName", recruiterName)
                .add("phone", phone)
                .add("email", email)
                .add("priority", priority)
                .toString();
    }

    /**
     * Returns a string representation of the company in the form of a list of attributes.
     * Used to display the entire string representation in one line
     */
    public String duplicatedCompanyFlagOutput() {
        return "Name = " + name + "," + " Role = " + role;
    }
}
