import { useState, useEffect } from "react";
import UserInfoRow from "./UserInfoRow";

export default function UsersContent() {
  const [users, setUsers] = useState([]);
  const [isEditable, setEditable] = useState(isAdmin());

  useEffect(() => {
    getUsers();
  }, []);

  function getUsers() {
    fetch("http://localhost:8080/api/users")
      .then(async (response) => {
        if (response.ok) {
          const data = await response.json();
          setUsers(data);
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

  return (
    <table>
      <thead>
        <tr>
          <th>User</th>
          <th>Role</th>
        </tr>
      </thead>
      <tbody>
        {users.map((user) => (
          <UserInfoRow key={user.username} isEditable={isEditable} {...user} />
        ))}
      </tbody>
    </table>
  );
}
