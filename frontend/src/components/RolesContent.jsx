import { useState, useEffect } from "react";
import RoleInfoRow from "./RoleInfoRow";

export default function RolesContent() {
  const [roles, setRoles] = useState([]);
  const [isEditable, setEditable] = useState(isAdmin());

  useEffect(() => {
    getRoles();
  }, []);

  function getRoles() {
    fetch("http://localhost:8080/api/roles")
      .then(async (response) => {
        if (response.ok) {
          const data = await response.json();
          setRoles(data);
        } else {
          return Promise.reject(response.status);
        }
      })
      .catch((error) => {
        console.error("There was an error!", error);
      });
  }

  function isAdmin() {
    return sessionStorage.getItem("role") === "ROLE_ADMIN";
  }

  async function processDeletePrivilege(roleId, privilegeId) {
    const deletePrivilegeRequest = {
      method: "DELETE",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
    };
    const responce = await fetch(
      `http://localhost:8080/api/roles/${roleId}/privileges/${privilegeId}`,
      deletePrivilegeRequest
    );
    if (responce.ok) {
      getRoles();
    }
  }

  async function processAddPrivilege(roleId, privilegeValue) {
    const addPrivilegeRequest = {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: privilegeValue,
    };
    const responce = await fetch(
      `http://localhost:8080/api/roles/${roleId}/privileges`,
      addPrivilegeRequest
    );
    if (responce.ok) {
      getRoles();
    }
  }

  return (
    <table>
      <thead>
        <tr>
          <th>Role</th>
          <th>Privileges</th>
        </tr>
      </thead>
      <tbody>
        {roles.map((role) => (
          <RoleInfoRow
            key={role.id}
            isEditable={isEditable}
            onDelete={processDeletePrivilege}
            onAdd={processAddPrivilege}
            {...role}
          />
        ))}
      </tbody>
    </table>
  );
}
