import { useState } from "react";

export default function SignInForm() {
  const [signinData, setSigninData] = useState({ username: "", password: "" });

  async function submitSignin() {
    const signinRequest = {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username: signinData.username,
        password: signinData.password,
      }),
    };

    const responce = await fetch(
      "http://localhost:8080/api/auth/register",
      signinRequest
    );
    if (responce.ok) {
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
          setSigninData({
            username: event.target.value,
            password: signinData.password,
          })
        }
      />
      <input
        type="password"
        name="password"
        placeholder="Password"
        onChange={(event) =>
          setSigninData({
            username: signinData.username,
            password: event.target.value,
          })
        }
      />
      <button onClick={submitSignin}>Sign In</button>

      <pre>
        username: {signinData.username}
        pass: {signinData.password}
      </pre>
    </div>
  );
}
