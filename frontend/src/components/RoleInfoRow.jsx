import PrivilegeRow from "./PrivilegeRow";
import AddPrivilegeRow from "./AddPrivilegeRow";

export default function RoleInfoRow({
  isEditable,
  onDelete,
  onAdd,
  id,
  name,
  privileges,
}) {
  function insertPrivilegeRow(privilege) {
    return isEditable ? (
      <PrivilegeRow
        key={privilege.id}
        onDelete={onDelete}
        roleId={id}
        privilege={privilege}
      />
    ) : (
      <div key={privilege.id}>
        {privilege.name}
        <br />
      </div>
    );
  }

  return (
    <tr>
      <td>{name}</td>
      <td>
        <>{privileges.map(insertPrivilegeRow)}</>
        <>{isEditable ? <AddPrivilegeRow onAdd={onAdd} roleId={id} /> : ""}</>
      </td>
    </tr>
  );
}
