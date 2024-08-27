import Button from "./Button/Button";

export default function PrivilegeRow({ onDelete, roleId, privilege }) {
  return (
    <div>
      {privilege.name}
      <Button onClick={() => onDelete(roleId, privilege.id)}>X</Button>
    </div>
  );
}
