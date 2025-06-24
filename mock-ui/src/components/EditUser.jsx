import axios from "axios";
import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

function EditUser() {
    const { id } = useParams();
    const navigate = useNavigate();

    const [msg, setMsg] = useState("");
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [gender, setGender] = useState("male");
    const [status, setStatus] = useState("active");

    useEffect(() => {
        const getUser = async () => {
            try {
                let response = await axios.get(`https://gorest.co.in/public/v2/users/${id}`);
                const data = response.data;
                setName(data.name);
                setEmail(data.email);
                setGender(data.gender);
                setStatus(data.status);
            } catch (error) {
                console.log("Error loading user:", error);
            }
        };
        getUser();
    }, [id]);

    const updateUser = async () => {
        try {
            const response = await axios.put(
                `https://gorest.co.in/public/v2/users/${id}`,
                {
                    name,
                    email,
                    gender,
                    status
                },
                {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem("token")}`,
                    },
                }
            );
            console.log("User updated:", response.data);
            setMsg("User updated successfully!");
            console.log(setMsg);
            navigate("/");
        } catch (error) {
            console.log("Update failed:", error);
            setMsg("Error: Could not update user.");
        }
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-3"></div>
                <div className="col-md-6 mt-5">
                    <div className="card mt-5">
                        <div className="card-header">
                            <h2>Edit User</h2>
                            {
                                msg !== "" ? <div className="row">
                                    <div className="col-lg-6">
                                        {msg}
                                    </div>
                                </div> : ""
                            }
                        </div>
                        <div className="card-body">
                            <div className="mb-2">
                                <label>Name:</label>
                                <input
                                    type="text"
                                    placeholder="Name"
                                    value={name}
                                    onChange={($e) => setName($e.target.value)}
                                    className="form-control"
                                />
                            </div>
                            <div className="mb-2">
                                <label>Email:</label>   
                                <input
                                    type="email"
                                    placeholder="Email"
                                    value={email}
                                    onChange={($e) => setEmail($e.target.value)}
                                    className="form-control"
                                />
                            </div>

                            <div className="mb-2">
                                <label className="me-2">Gender:</label>
                                <label className="me-2">
                                    <input
                                        type="radio"
                                        value="male"
                                        checked={gender === "male"}
                                        onChange={($e) => setGender($e.target.value)}
                                    />
                                    Male
                                </label>
                                <label>
                                    <input
                                        type="radio"
                                        value="female"
                                        checked={gender === "female"}
                                        onChange={($e) => setGender($e.target.value)}
                                    />
                                    Female
                                </label>
                            </div>

                            <div className="mb-2">
                                <label className="me-2">Status:</label>
                                <label className="me-2">
                                    <input
                                        type="radio"
                                        value="active"
                                        checked={status === "active"}
                                        onChange={(e) => setStatus(e.target.value)}
                                    />
                                    Active
                                </label>
                                <label>
                                    <input
                                        type="radio"
                                        value="inactive"
                                        checked={status === "inactive"}
                                        onChange={(e) => setStatus(e.target.value)}
                                    />
                                    Inactive
                                </label>
                            </div>
                        </div>
                        <div className="card-footer">
                            <button className="btn btn-primary" onClick={updateUser}>
                                Update
                            </button>
                        </div>
                    </div>
                </div>
                <div className="col-md-3"></div>
            </div>
        </div>
    );
}

export default EditUser;
