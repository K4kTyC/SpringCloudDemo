import { useState } from "react";

export default function LogInForm() {
  const [loginData, setLoginData] = useState({ username: "", password: "" });

  async function submitLogin(event) {
    const loginRequest = {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: new URLSearchParams({
        username: loginData.username,
        password: loginData.password,
      }),
    };

    const response = await fetch(
      "http://localhost:8080/api/auth/login",
      loginRequest
    );
    if (response.ok) {
      location.assign("http://localhost:8080/oauth2/authorization/spring");
    }
  }

  return (
    <div>
      <input
        type="text"
        name="username"
        placeholder="Username"
        onChange={(event) =>
          setLoginData({
            username: event.target.value,
            password: loginData.password,
          })
        }
      />
      <input
        type="password"
        name="password"
        placeholder="Password"
        onChange={(event) =>
          setLoginData({
            username: loginData.username,
            password: event.target.value,
          })
        }
      />
      <a onClick={submitLogin}>LogIn</a>

      <pre>
        username: {loginData.username}
        pass: {loginData.password}
      </pre>
    </div>
  );
}
