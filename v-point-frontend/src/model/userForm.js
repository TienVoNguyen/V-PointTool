export default class UserForm {
    constructor(staffId, fullname, email, password, confirmPassword, role = [], department) {
        this.staffId = staffId;
        this.fullname = fullname;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.department = department;
        this.role = role;
    }
}
