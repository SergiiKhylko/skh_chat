import './App.css';
import {useCallback, useEffect, useRef, useState} from "react";

function App() {
  const [messages, setMessages] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);
  const textRef = useRef("");

  const fetchMessageHandler = useCallback(async () => {
    setIsLoading(true);
    setError(null);
    try {
      const response = await fetch("http://localhost:8080/api/v1/messages");

      if (!response.ok) {
        throw new Error("Something went wrong...");
      }

      const data = await response.json();
      const loadedMessages = data.map((message) => ({
        id: message.id,
        text: message.text,
        sentTime: message.sentTime,
      }));
      setMessages(loadedMessages);
    } catch (error) {
      setError(error.message);
    }
    setIsLoading(false);
  }, []);

  useEffect(() => {
    fetchMessageHandler();
  }, [fetchMessageHandler]);

  let content = <h1>No messages yet.</h1>;

  if (messages.length > 0) {
    content = messages.map((message) => (
      <div key={message.id} className="chat-message">
        {message.text}
      </div>
    ));
  }

  if (error) {
    content = <p>{error}</p>;
  }

  if (isLoading) {
    content = <p>Loading chat history...</p>;
  }

  async function addMessageHandler() {

    const message = {
      text: textRef.current.value
    };

    const response = await fetch(
      "http://localhost:8080/api/v1/messages",
      {
        method: "POST",
        body: JSON.stringify(message),
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    const data = await response.json();
    console.log(data);
    fetchMessageHandler();
    textRef.current.value = "";
  }

  return (
    <div className="App">
      <div className="chat">
        <div className="chat-header">
          <h3>SKH Chat</h3>
        </div>
        <div className="chat-messages">{content}</div>
        <div className="chat-input">
          <input type="text" placeholder="Введіть повідомлення" ref={textRef} />
          <button onClick={addMessageHandler}>Надіслати</button>
        </div>
      </div>
    </div>
  );
}

export default App;
