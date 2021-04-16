import React, { useState, useEffect } from "react";
import { render } from "react-dom";
import axios from "axios";
import FbLogin from "../Facebook";

const ReactFBPageRandomQuote = () => {
  const postRandomQuote = () => {
    axios
      .post("https://graph.facebook.com/100135305101505/photos?", {
        url: "https://source.unsplash.com/featured/?quote",
        access_token:
          "EAAE5NxTtFmMBADmKLADReX9fZCa3l4WTxlOSpMaPRhtYgfB23NvelzXShYE3nmO2mSk8IqbZAY2DAvjMZBQA8ijkrQXaIh5ZAaOaK0vNEZAgTcM3j6v5ugNJ1Deu2kWiG6EzkuTKb9PZCpUDVUZAQFJiaOnGoGfeB6aNkVAg1bjtUxZAEBCJjYlioXiSdGSooNsZD"
      })
      .then(
        res => {
          const result = res.data;
          console.log(result);
          alert("Success!");
        },
        error => {
          console.log(error);
        }
      );
  };

  return (
    <div>
      <FbLogin/>
      <button onClick={() => postRandomQuote()}>Post Photo</button>
    </div>
  );
};

export default ReactFBPageRandomQuote