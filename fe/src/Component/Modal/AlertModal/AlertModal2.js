import { Modal, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

import classes from "./AlertModal.module.css";

export default function AlertModal2(props) {
  const { show2, message2, selectedPrd } = props.value;
  const activateFunction = props.activeFunc;
  const setSelectedPrd = props.initSelected;

  function handlerClose() {
    const modalClose = props.func;
    modalClose();
  }
  function activeHandlerClose(){
    const modalClose = props.func;
    setSelectedPrd({...selectedPrd, id : -1});
    modalClose();
    activateFunction();
  }
  return (
    <Modal show={show2} onHide={handlerClose} className={classes.modal}>
      <Modal.Header>알림</Modal.Header>
      <Modal.Body>{message2}</Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={activeHandlerClose}>
          확인
        </Button>
        <Button variant="secondary" onClick={handlerClose}>
          취소
        </Button>
      </Modal.Footer>
    </Modal>
  );
}
