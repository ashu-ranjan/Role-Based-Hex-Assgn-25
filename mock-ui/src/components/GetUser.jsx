import axios from "axios";
import { useState } from "react";

function GetUserById() {
  const [id, setId] = useState("");
  const [user, setUser] = useState({});
  const [msg, setMsg] = useState("");

  const getUser = async () => {
    try {
      let response = await axios.get(`https://gorest.co.in/public/v2/users/${id}`);
      setUser(response.data);
      setMsg("");
      console.log(response.data);
    } catch (error) {
      setUser({});
      setMsg("User not found or invalid ID");
      console.log(error);
    }
  };

  return (
    <div className="container mt-5">
      <div className="row">
        <div className="col-md-3"></div>
        <div className="col-md-6">
          <div className="card">
            <div className="card-header">
              <h2>Get User by ID</h2>
              {msg !== "" && <div className="text-danger">{msg}</div>}
            </div>
            <div className="card-body">
              <div className="mb-2">
                <input
                  type="number"
                  placeholder="Enter ID"
                  onChange={($e) => setId($e.target.value)}
                  className="form-control"
                />
              </div>
              <button className="btn btn-primary" onClick={getUser}>
                Fetch User
              </button>
              <hr />
              {user.name && (
                <div>
                  <p><b>ID:</b> {user.id}</p>
                  <p><b>Name:</b> {user.name}</p>
                  <p><b>Email:</b> {user.email}</p>
                  <p><b>Gender:</b> {user.gender}</p>
                  <p><b>Status:</b> {user.status}</p>
                </div>
              )}
            </div>
          </div>
        </div>
        <div className="col-md-3"></div>
      </div>
    </div>
  );
}

export default GetUserById;
