import { useState } from "react";
import Button from "./Button/Button";

export default function AddPrivilegeRow({ onAdd, roleId }) {
  const [privilegeValue, setPrivilegeValue] = useState("");

  function processAddButtonClick() {
    onAdd(roleId, privilegeValue);
    setPrivilegeValue("");
  }

  return (
    <>
      <input
        type="text"
        value={privilegeValue}
        onChange={(e) => setPrivilegeValue(e.target.value.toLowerCase())}
      />
      <Button onClick={processAddButtonClick}>Add</Button>
    </>
  );
}
