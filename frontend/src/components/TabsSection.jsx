import Button from "./Button/Button";

export default function TabsSection({ activeTab, onChange }) {
  return (
    <section style={{ marginBottom: "1rem" }}>
      <Button isActive={activeTab === "jwt"} onClick={() => onChange("jwt")}>
        JWT
      </Button>

      <Button
        isActive={activeTab === "users"}
        onClick={() => onChange("users")}
      >
        Users
      </Button>

      <Button
        isActive={activeTab === "roles"}
        onClick={() => onChange("roles")}
      >
        Roles
      </Button>
    </section>
  );
}
