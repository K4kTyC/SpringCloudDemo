import EditableRoleCell from "./EditableRoleCell";

export default function UserInfoRow({ isEditable, id, username, role }) {
  return (
    <tr>
      <td>{username}</td>
      <td>
        {isEditable ? <EditableRoleCell userId={id} role={role} /> : { role }}
      </td>
    </tr>
  );
}
