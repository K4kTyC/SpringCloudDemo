import { useState } from "react";
import Modal from "./Modal/Modal";
import LogInForm from "./LoginForm";
import SignInForm from "./SignInForm";

export default function AnonHeaderContent() {
  const [isModalOpen, setModalOpen] = useState(false);
  const [modalContent, setModalContent] = useState();

  function openLogInModal() {
    setModalContent(
      <>
        <LogInForm />
        <button onClick={() => setModalOpen(false)}>Close</button>
      </>
    );
    setModalOpen(true);
  }

  function openSignInModal() {
    setModalContent(
      <>
        <SignInForm />
        <button onClick={() => setModalOpen(false)}>Close</button>
      </>
    );
    setModalOpen(true);
  }

  return (
    <>
      <div onClick={openLogInModal}>Log In</div>
      <div onClick={openSignInModal}>Sign In</div>
      <Modal open={isModalOpen} children={modalContent} />
    </>
  );
}
