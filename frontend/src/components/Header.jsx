import AnonHeaderContent from "./AnonHeaderContent";
import UserHeaderContent from "./UserHeaderContent";

export default function Header({ isLoggedIn, doOnLogout }) {
  return (
    <header>
      {isLoggedIn ? (
        <UserHeaderContent doOnLogout={doOnLogout} />
      ) : (
        <AnonHeaderContent />
      )}
    </header>
  );
}
