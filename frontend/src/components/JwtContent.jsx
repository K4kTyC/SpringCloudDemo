import { useState } from "react";

export default function JwtContent() {
  const [jwt, setJwt] = useState();

  async function loadJwt() {
    const respones = await fetch("http://localhost:8080/api/token", {
      credentials: "include",
    });
    setJwt(await respones.json());
  }

  return (
    <>
      <div>
        <button onClick={loadJwt}>Load jwt</button>
        <pre>{JSON.stringify(jwt, null, 2)}</pre>
      </div>
    </>
  );
}
