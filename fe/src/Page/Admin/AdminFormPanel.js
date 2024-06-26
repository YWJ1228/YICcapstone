import { useState, useEffect } from "react";
import { API } from "../../Config/APIConfig";
import { PageConfig } from "../../Config/Config";
import { getCookies } from "../../Component/Cookies/LoginCookie";

import Form from "react-bootstrap/Form";
import Stack from "@mui/material/Stack";
import axios from "axios";

import AdminListPanel from "./AdminListPanel";
import AdminVoiceForm from "./Form/AdminVoiceForm";
import AdminEbookForm from "./Form/AdminEbookForm";
import AdminInquiryForm from "./Form/AdminInquiryForm";

export default function AdminFormPanel(props) {
  const [task, setTask] = useState(0);
  const [listProduct, setListProduct] = useState([]);
  const [curProduct, setCurProduct] = useState(PageConfig.VOICE_PAGE_DEFAULT_STATE);
  const [curEbook, setCurEbook] = useState();
  const [curInquiry, setCurInquiry] = useState();
  useEffect(() => {
    if (props.type === "voice") {
      // get page size
      axios.get(`${API.ADMIN_LOAD_VOICELIST_SIZE}`).then((res) => {
        axios.get(`${API.ADMIN_LOAD_VOICELIST}${res.data.totalElements}`).then((res2) => {
          const resData = res2.data.content.map((voice) => ({
            id: voice.id,
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
      });
    } else if (props.type === "ebook") {
      //get page size
      axios.get(`${API.ADMIN_LOAD_EBOOKLIST_SIZE}`).then((res) => {
        axios.get(`${API.ADMIN_LOAD_EBOOKLIST}${res.data.totalElements}`).then((res2) => {
          setListProduct(res2.data.content);
        });
      });
    } else if (props.type === "inquiry") {
      axios
        .get(`${API.ADMIN_LOAD_FEEDBACK_LIST_SIZE}`, {
          headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
        })
        .then((res) => {
        
          if (res.data.totalElements !== 0) {
            axios
              .get(`${API.ADMIN_LOAD_FEEDBACK_LIST}${res.data.totalElements}`, {
                headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
              })
              .then((res2) => {
                setListProduct(res2.data.content);
              });
          }
        })
        .catch((err) => {
          // console.log(err);
        });
    }
  }, [task]);

  function submitHandler(event) {
    event.preventDefault();
    switch (task) {
      case 0:
        addProductHandler(event.target);
        break;
      case 1:
        changeProductHandler(event.target);
        break;
      case 2:
        deleteProductHandler(event.target);
        break;
      case 3:
        initFormHandler(event.target);
        break;
      case 4:
        loadProductHandler(event.target);
        break;
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
    } else if (props.type === "ebook") {
      form.id.value = curEbook.id;
      form.category.value = curEbook.category;
      form.name.value = curEbook.ebookName;
      form.author.value = curEbook.author;
      form.publisher.value = curEbook.publisher;
      form.pages.value = curEbook.pages;
      form.comment.value = curEbook.comment;
      form.content.value = curEbook.content;
      form.viewCount.value = curEbook.viewCount;
      form.purchaseCount.value = curEbook.purchaseCount;
      form.rating.value = curEbook.rating;
      form.imageUrl.value = curEbook.imageUrl;
      form.price.value = curEbook.price;
    } else if (props.type === "inquiry") {
      if(curInquiry.id !== 'default'){
      form.id.value = curInquiry.id;
      form.username.value = curInquiry.username;
      form.title.value = curInquiry.title;
      form.detail.value = curInquiry.detail;
      form.createdAt.value = curInquiry.createdAt;
      }
    }
  }
  function addProductHandler(form) {
    if (props.type === "voice") {
      axios
        .post(
          `${API.ADMIN_ADD_VOICE}`,
          {
            voiceModelUrl: form.voiceUrl.value.trim(),
            celebrityName: form.name.value.trim(),
            price: Number(form.price.value),
            imageUrl: form.imageUrl.value.trim(),
            comment: form.comment.value.trim(),
            sampleUrl: form.demoUrl.value.trim(),
            category: form.category.value.trim(),
          },
          {
            headers: {
              Authorization: `Bearer ${getCookies("accessToken")}`,
            },
          }
        )
        .then((res) => {
          // console.log("추가 완료");
          initFormHandler(form);
        })
        .catch((err) => {
          // console.log(err);
        });
    } else {
      axios
        .post(
          `${API.ADMIN_ADD_EBOOK}`,
          {
            ebookName: form.name.value.trim(),
            author: form.author.value.trim(),
            pages: Number(form.pages.value),
            publisher: form.publisher.value.trim(),
            price: Number(form.price.value),
            imageUrl: form.imageUrl.value.trim(),
            comment: form.comment.value.trim(),
            content: form.content.value.trim(),
            category: form.category.value.trim(),
          },
          {
            headers: {
              Authorization: `Bearer ${getCookies("accessToken")}`,
            },
          }
        )
        .then((res) => {
          // console.log("추가 완료");
          initFormHandler(form);
        })
        .catch((err) => {
          // console.log(err);
        });
    }
  }

  function changeProductHandler(form) {
    if (props.type === "voice") {
      axios
        .put(
          `${API.ADMIN_UPDATE_VOICE}${curProduct.id}`,
          {
            voiceModelUrl: form.voiceUrl.value.trim(),
            celebrityName: form.name.value.trim(),
            price: Number(form.price.value),
            imageUrl: form.imageUrl.value.trim(),
            comment: form.comment.value.trim(),
            sampleUrl: form.demoUrl.value.trim(),
            category: form.category.value.trim(),
          },
          {
            headers: {
              Authorization: `Bearer ${getCookies("accessToken")}`,
            },
          }
        )
        .then((res) => {
          // console.log("수정완료");
          initFormHandler(form);
        })
        .catch((err) => {
          // console.log(err);
        });
    } else {
      axios
        .put(
          `${API.ADMIN_UPDATE_EBOOK}${curEbook.id}`,
          {
            ebookName: form.name.value.trim(),
            author: form.author.value.trim(),
            pages: Number(form.pages.value),
            publisher: form.publisher.value.trim(),
            price: Number(form.price.value),
            imageUrl: form.imageUrl.value.trim(),
            comment: form.comment.value.trim(),
            content: form.content.value.trim(),
            category: form.category.value.trim(),
          },
          {
            headers: {
              Authorization: `Bearer ${getCookies("accessToken")}`,
            },
          }
        )
        .then((res) => {
          // console.log("수정완료");
          initFormHandler(form);
        })
        .catch((err) => {
          // console.log(err);
        });
    }
  }
  function deleteProductHandler(form) {
    if (props.type === "voice") {
      axios
        .delete(`${API.ADMIN_DELETE_VOICE}${curProduct.id}`, {
          headers: {
            Authorization: `Bearer ${getCookies("accessToken")}`,
          },
          data: {},
        })
        .then((res) => {
          // console.log("삭제 완료");
          initFormHandler(form);
        })
        .catch((err) => {
          // console.log(err);
        });
    } else if(props.type === 'ebook'){
      axios
        .delete(`${API.ADMIN_DELETE_EBOOK}${curEbook.id}`, {
          headers: {
            Authorization: `Bearer ${getCookies("accessToken")}`,
          },
          data: {},
        })
        .then((res) => {
          // console.log("삭제 완료");
          initFormHandler(form);
        })
        .catch((err) => {
          // console.log(err);
        });
    }
    else{
      if(listProduct.length !== 0){
      axios
        .delete(`${API.ADMIN_DELETE_FEEDBACK}${curInquiry.id}`, {
          headers: {
            Authorization: `Bearer ${getCookies("accessToken")}`,
          },
          data: {},
        })
        .then((res) => {
          // console.log("삭제 완료");
          initFormHandler(form);
        })
        .catch((err) => {
          // console.log(err);
        });
    }
  }
  }
  function initFormHandler(form) {
    Array.from(form.elements).forEach((input) => {
      if (input.type == "text" || input.type == "number") {
        input.value = "";
      }
    });
  }
  function ItemClickHandler(prd, type) {
    if (type === "voice") {
      setCurProduct(prd);
    } else if (type === "ebook") {
      setCurEbook(prd);
    } else if (type === "inquiry") {
      setCurInquiry(prd);
    }
    setTask(4);
  }
  function FormType(type) {
    if (type === "voice") {
      return <AdminVoiceForm setTask={setTask} />;
    } else if (type === "ebook") {
      return <AdminEbookForm setTask={setTask} />;
    } else if (type === "inquiry") {
      return <AdminInquiryForm setTask={setTask} />;
    }
  }
  return (
    <Form onSubmit={submitHandler}>
      <Stack direction="row" spacing={5}>
        {FormType(props.type)}
        <AdminListPanel clickHandler={ItemClickHandler} listOfVoice={listProduct} type={props.type} render={task}/>
      </Stack>
    </Form>
  );
}
