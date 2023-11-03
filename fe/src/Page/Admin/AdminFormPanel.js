import { useState, useEffect } from "react";
import { API } from "../../Config/APIConfig";
import { PageConfig } from "../../Config/Config";

import Form from "react-bootstrap/Form";
import Stack from "@mui/material/Stack";
import axios from "axios";

import AdminListPanel from "./AdminListPanel";
import AdminVoiceForm from "./Form/AdminVoiceForm";
import AdminEbookForm from "./Form/AdminEbookForm";
import AdminUserForm from "./Form/AdminUserForm";

export default function AdminFormPanel(props) {
  const [task, setTask] = useState(0);
  const [curProduct, setCurProduct] = useState(PageConfig.VOICE_PAGE_DEFAULT_STATE);
  const [listProduct, setListProduct] = useState([PageConfig.VOICE_PAGE_DEFAULT_STATE]);
  useEffect(() => {
    if (props.type === "voice") {
      axios.get(`${API.ADMIN_LOAD_VOICELIST}`).then((res) => {
        const resData = res.data.content.map((voice) => ({
          voiceUrl: voice.voiceModelUrl,
          name: voice.celebrityName,
          price: voice.price,
          image: voice.imageUrl,
          description: voice.comment,
          demoUrl: voice.sampleUrl,
          job: voice.category,
        }));
        setListProduct(resData);
      });
    }
    else if(props.type === "ebook"){
      axios.get(`${API.ADMIN_LOAD_EBOOKLIST}`).then((res) => {
        const resData = res.data.content.map((book) => ({

        }));
        setListProduct(resData);
      });
    }
    else if(props.type === "user"){
      axios.get(`${API.ADMIN_LOAD_EBOOKLIST}`).then((res) => {
        const resData = res.data.content.map((book) => ({

        }));
        setListProduct(resData);
      });
    }
  }, [task]);

  function submitHandler(event) {
    event.preventDefault();
    switch (task) {
      case 0:addProductHandler(event.target);break;
      case 1:changeProductHandler(event.target);break;
      case 2:deleteProductHandler(event.target);break;
      case 3:initFormHandler(event.target);break;
      case 4:loadProductHandler(event.target);break;
    }
  }

  function loadProductHandler(form) {
    if (props.type === "voice") {
      form.id.value = curProduct.id;
      form.name.value = curProduct.name;
      form.price.value = curProduct.price;
      form.comment.value = curProduct.description;
      form.category.value = curProduct.job;
      form.imageUrl.value = curProduct.image;
      form.voiceUrl.value = curProduct.voiceUrl;
      form.demoUrl.value = curProduct.demoUrl;
    } else if (props.type === "user") {
    }
  }
  function addProductHandler(form) {
    axios
      .post(`${API.ADMIN_ADD_VOICE}`, {
        voiceModelUrl: form.voiceUrl.value.trim(),
        celebrityName: form.name.value.trim(),
        price: form.price.value,
        imageUrl: form.imageUrl.value.trim(),
        comment: form.comment.value.trim(),
        sampleUrl: form.demoUrl.value.trim(),
        category: form.category.value.trim(),
      })
      .then((res) => {
        console.log(res);
        console.log("추가 완료");
        initFormHandler(form);
      })
      .catch((err) => {
        console.log(err);
      });
  }

  function changeProductHandler(form) {
    axios
      .put(`${API.ADMIN_ADD_VOICE}/${curProduct.id}`, {
        voiceModelUrl: form.voiceUrl.value.trim(),
        celebrityName: form.name.value.trim(),
        price: form.price.value,
        imageUrl: form.imageUrl.value.trim(),
        comment: form.comment.value.trim(),
        sampleUrl: form.demoUrl.value.trim(),
        category: form.category.value.trim(),
      })
      .then((res) => {
        console.log(res);
        console.log("수정완료");
        initFormHandler(form);
      })
      .catch((err) => {
        console.log(err);
      });
  }
  function deleteProductHandler(form) {
    axios
      .delete(`${API.ADMIN_ADD_VOICE}/${curProduct.id}`)
      .then((res) => {
        console.log(res);
        console.log("삭제 완료");
        initFormHandler(form);
      })
      .catch((err) => {
        console.log(err);
      });
  }
  function initFormHandler(form) {
    Array.from(form.elements).forEach((input) => {
      if (input.type == "text" || input.type == "number") {
        input.value = "";
      }
    });
  }
  function ItemClickHandler(prd) {
    setCurProduct(prd);
    setTask(4);
  }
  function FormType(type){
    if(type === 'voice'){
      return <AdminVoiceForm setTask = {setTask}/>
    }
    else if(type === 'ebook'){
      return <AdminEbookForm setTask = {setTask}/>
    }
    else if(type ==='user'){
      return <AdminUserForm setTask = {setTask}/>
    }
  }
  return (
    <Form onSubmit={submitHandler}>
      <Stack direction="row" spacing={5}>
        {FormType(props.type)}
        <AdminListPanel clickHandler={ItemClickHandler} listOfVoice={listProduct} />
      </Stack>
    </Form>
  );
}
