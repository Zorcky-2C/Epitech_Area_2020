import React, { useState, useEffect } from "react";
import { render } from "react-dom";
import axios from "axios";

const ReactFBPageRandomQuote = () => {
  const [quote, setQuote] = useState("");

  // useEffect(() => {
  //   axios.get("https://quotes.rest/qod?language=en").then(res => {
  //     const result = res.data;
  //     console.log(result);
  //     setQuote(result.contents.quotes[0].quote);
  //   });
  // });

  useEffect(() => {
    axios.get("https://api.quotable.io/random").then(res => {
      const result = res.data;
      console.log(result.content);
      setQuote(result.content);
    });
  }, []);

  const postRandomQuote = () => {
    axios
      .post("https://graph.facebook.com/100135305101505/feed?", {
        message: quote,
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
      React Facebook Post Page Status - Random Quote
      <br />
      <br />
      {quote}
      <br />
      <button onClick={() => postRandomQuote()}>Post Status</button>
    </div>
  );
};
export default ReactFBPageRandomQuote;