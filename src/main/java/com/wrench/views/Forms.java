package com.wrench.views;

public enum Forms {
    LOGIN("""
            <form method="post" action="/login">
                <h1>Login</h1>
                
                <label>Username: </label>
                <input type="text" name="username" placeholder="Input username">
                <br>
                
                <label>Password: </label>
                <input type="text" name="password" placeholder="Input password">
                <input type="submit" name="submit" value="Submit">
                <br>
            </form>
                """),
    SIGNUP("""
            <form method="post" action="/signup">
                <h1>Sign Up</h1>
                
                <label>First Name: </label>
                <input type="text" name="firstname">
                <br>
                
                <label>Last Name: </label>
                <input type="text" name="lastname">
                <br>
                
                <label>Age: </label>
                <input type="number" name="age" value="18">
                <br>
                
                <label>Username: </label>
                <input type="text" name="username" placeholder="Input username">
                <br>
                
                <label>Password: </label>
                <input type="text" name="password" placeholder="Input password">
                <br>
                
                <label><abbr title="Date of Birth">DOB: </abbr></label>
                <input type="date" name="dateOfBirth" 
                <br>
                <br>
                <input type="submit" name="submit" value="Submit">
                <br>
            </form>
            """);

    private String rep;
    Forms(String rep) {
        this.rep = rep;
    }

    public String getRep() {
        return rep;
    }
}
