package seedu.address.testutil;


import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditCompanyDescriptor;
import seedu.address.model.company.Company;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;
import seedu.address.model.company.Priority;

/**
 * A utility class to help with building EditCompanyDescriptor objects.
 */
public class EditCompanyDescriptorBuilder {

    private EditCompanyDescriptor descriptor;

    public EditCompanyDescriptorBuilder() {
        descriptor = new EditCompanyDescriptor();
    }

    public EditCompanyDescriptorBuilder(EditCompanyDescriptor descriptor) {
        this.descriptor = new EditCompanyDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCompanyDescriptor} with fields containing {@code company}'s details
     */
    public EditCompanyDescriptorBuilder(Company company) {
        descriptor = new EditCommand.EditCompanyDescriptor();
        descriptor.setName(company.getName());
        descriptor.setPhone(company.getPhone());
        descriptor.setEmail(company.getEmail());
    }

    /**
     * Sets the {@code Name} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(new Priority(priority));
        return this;
    }

    public EditCompanyDescriptor build() {
        return descriptor;
    }
}
