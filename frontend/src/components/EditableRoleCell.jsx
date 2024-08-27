import { useState } from "react";
import Button from "./Button/Button";

export default function EditableRoleCell({ userId, role }) {
  const [backendRoleValue, setBackendRoleValue] = useState(role);
  const [roleValue, setRoleValue] = useState(role);

  async function processButtonClick() {
    if (backendRoleValue !== roleValue) {
      const updateRoleRequest = {
        method: "PUT",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          role: roleValue,
        }),
      };
      const responce = await fetch(
        `http://localhost:8080/api/users/${userId}/role`,
        updateRoleRequest
      );
      if (responce.ok) {
        const newRoleValue = (await responce.json()).role;
        setRoleValue(newRoleValue);
        setBackendRoleValue(newRoleValue);
      } else {
        setRoleValue(backendRoleValue);
      }
    }
  }

  return (
    <>
      <input
        type="text"
        value={roleValue}
        onChange={(e) => setRoleValue(e.target.value.toUpperCase())}
      />
      <Button onClick={() => processButtonClick()}>Edit</Button>
    </>
  );
}
