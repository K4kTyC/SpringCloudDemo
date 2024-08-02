import { useState, useEffect } from "react";
import Header from "./components/Header";
import TabsSection from "./components/TabsSection";
import JwtContent from "./components/JwtContent";
import UsersContent from "./components/UsersContent";

function App() {
  const [activeTab, setActiveTab] = useState("jwt");
  const [isLoggedIn, setLoggedIn] = useState(false);

  useEffect(() => {
    getUserInfo();
  }, []);

  function getUserInfo() {
    fetch("http://localhost:8080/api/auth/userinfo").then(async (response) => {
      if (response.ok) {
        const data = await response.json();
        setLoggedIn(true);
        sessionStorage.setItem("username", data.username);
        sessionStorage.setItem("role", data.role);
      }
    });
  }

  function doOnLogout() {
    setLoggedIn(false);
    sessionStorage.clear();
  }

  return (
    <>
      <Header isLoggedIn={isLoggedIn} doOnLogout={doOnLogout} />
      <main>
        <TabsSection
          activeTab={activeTab}
          onChange={(activeTab) => setActiveTab(activeTab)}
        />

        {activeTab === "jwt" && <JwtContent />}
        {activeTab === "users" && <UsersContent />}
      </main>
    </>
  );
}

export default App;
