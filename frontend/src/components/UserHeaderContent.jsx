export default function UserHeaderContent({ doOnLogout }) {
  async function logout() {
    const logoutRequest = {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      credentials: "include",
      body: "",
    };

    fetch("http://localhost:8080/api/auth/logout", logoutRequest)
      .then((response) => {
        if (response.ok) {
          doOnLogout();
        }
        return Promise.reject(response.status);
      })
      .catch((error) => {
        console.error("There was an error!", error);
      });
  }

  return (
    <>
      <div onClick={logout}>Log Out</div>
      <a href="#">{sessionStorage.getItem("username")}</a>
    </>
  );
}
