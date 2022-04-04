import {Button, Form, Modal, Pagination, Table} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import ContactService from "../services/ContactService";
import contactService from "../services/ContactService";

function PhoneBookPage(props) {

    const [totalPages, setTotalPages] = useState(null);
    const [currentPage, setCurrentPage] = useState(null);
    const [contacts, setContacts] = useState(null);
    const [contactTextInput, setContactTextInput] = useState(null);

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const [contactForAction, setContactForAction] = useState(null);

    const [modalTitle, setModalTitle] = useState("");

    // const [contactActionButtonTitle, setContactActionButtonTitle] = useState("");

    function handleSubmit(event) {
        event.preventDefault();
        event.stopPropagation();
        let contactTextInput = event.target.elements.contactTextInput.value;
        setContactTextInput(contactTextInput);

    }

    useEffect(() => {
        fetchPageData()
    }, []);

    useEffect(() => {
        fetchPageData()
    }, [contactTextInput]);

    function fetchPageData(page = 0) {
        let contactPromise;

        if (contactTextInput) {
            contactPromise = ContactService.getContacts(page, contactTextInput);
        } else {
            contactPromise = ContactService.getContacts(page);
        }
        contactPromise
            .then((res) => {
                setContacts(res.data.contacts);
                setTotalPages(res.data.totalPages);
                setCurrentPage(res.data.currentPage);
            })
            .catch((err) => {
                    console.log(err)
                    if (err.response.status == 404) {
                        setContacts(null);
                        setTotalPages(0);
                        setCurrentPage(0);
                    }
                }
            );

    }

    function updateContactHandler(contact) {
        setContactForAction(contact);
        setModalTitle("Aktualizuj kontakt");
        setShow(true);
    }

    function deleteContactHandler(id) {
        contactService.deleteContact(id).then(res => {
            console.log(res.status);
            fetchPageData();
        })

    }

    function createContactHandler() {
        setModalTitle("Dodaj Kontakt");
        setShow(true);
    }

    // if (contacts)
    return (
        <>
            <div className="d-grid gap-2">
                <Button className={"mt-1 mb-3"}
                        onClick={() => {
                            createContactHandler();
                        }}
                >
                    Dodaj kontakt
                </Button>
            </div>
            <Form
                onSubmit={handleSubmit}
            >
                <Form.Group className="mb-2">
                    <Form.Label for="contactTextInput">Szukaj kontaktów: </Form.Label>
                    <Form.Control
                        type="text"
                        name="contactTextInput"
                        className="form-control"
                        id="contactTextInput"
                        aria-describedby="contactTextInputHelp"
                        placeholder="Wprowadź dane do wyszukiwania"
                        />

                        <Button className="mt-2" type="submit" variant="primary">
                            Szukaj
                        </Button>
                    </Form.Group>
                </Form>



                <Table className="my-3" striped bordered hover>
                    <thead>
                    <tr>
                        <th>Imię</th>
                        <th>Nazwisko</th>
                        <th>Numer telefonu domowego</th>
                        <th>Numer telefonu służbowego</th>
                        <th>Email</th>
                        <th colSpan={2}>Akcje</th>
                    </tr>
                    </thead>

                    <tbody>

                    {contacts && contacts.map((contact) => {
                        return (
                            <tr>
                                <td>{contact.firstName}</td>
                                <td>{contact.lastName}</td>
                                <td>{contact.homePhoneNumber}</td>
                                <td>{contact.businessPhoneNumber}</td>
                                <td>{contact.email}</td>
                                <td>
                                    <Button
                                        onClick={() => {
                                            updateContactHandler(contact);
                                        }}
                                    >
                                        Aktualizuj
                                    </Button>
                                </td>
                                <td>
                                    <Button
                                        variant="secondary"
                                        onClick={() => {
                                            deleteContactHandler(contact.id);
                                        }}
                                    >
                                        Usuń
                                    </Button>
                                </td>
                            </tr>
                        );
                    })}
                    </tbody>
                </Table>

                {totalPages !== null &&
                    currentPage !== null &&
                    (() => {
                        let active = currentPage + 1;
                        let items = [];
                        for (let number = 1; number <= totalPages; number++) {
                            items.push(
                                <Pagination.Item
                                    key={number}
                                    onClick={() => {
                                        fetchPageData(number - 1);
                                        console.log(number);
                                    }}
                                    active={number === active}
                                >
                                    {number}
                                </Pagination.Item>
                            );
                        }
                        return (
                            <div className="text-center">
                                <Pagination>{items}</Pagination>
                            </div>
                        );
                    })()}

            {/*{contactForAction &&*/}
            <Modal
                show={show}
                onHide={handleClose}
                backdrop="static"
                keyboard={false}
            >
                <Modal.Header closeButton>
                    <Modal.Title>{modalTitle}</Modal.Title>
                </Modal.Header>
                <Modal.Body>

                    <Form
                        id="update-contact-form"
                        onSubmit={(event) => {
                            event.preventDefault();
                            event.stopPropagation();

                            let formData = new FormData(event.target);
                            let contactRequest = JSON.stringify(Object.fromEntries(formData));
                            console.log(contactRequest);
                            let actionPromise;
                            if (contactForAction) {
                                actionPromise = contactService.updateContact(contactForAction.id, contactRequest);
                            } else {
                                actionPromise = contactService.createContact(contactRequest);
                            }
                            actionPromise.then(
                                (res) => {
                                    fetchPageData();
                                    handleClose();
                                    setContactForAction(null);
                                }
                            ).catch(err => console.log(err));

                        }}

                    >
                        <Form.Group>
                            <Form.Label for="firstName">Imię</Form.Label>
                            <Form.Control
                                required
                                type="text"
                                name="firstName"
                                id="firstName"
                                placeholder="Wprowadź imię"
                                defaultValue={
                                    contactForAction ? contactForAction.firstName : ""
                                }
                            />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label for="lastName">Nazwisko</Form.Label>
                            <Form.Control
                                required
                                type="text"
                                name="lastName"
                                id="lastName"
                                placeholder="Wprowadź nazwisko"
                                defaultValue={
                                    contactForAction ? contactForAction.lastName : ""
                                }
                            />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label for="">Numer telefonu domowego</Form.Label>
                            <Form.Control
                                required
                                type="text"
                                pattern="\d{9}"
                                name="homePhoneNumber"
                                id="homePhoneNumber"
                                placeholder="Wprowadź numer telefonu domowego"
                                maxLength={9}
                                minLength={9}
                                defaultValue={contactForAction ? contactForAction.homePhoneNumber : ""}
                            />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label for="">Numer telefonu służbowego</Form.Label>
                            <Form.Control
                                required
                                type="text"
                                pattern="\d{9}"
                                name="businessPhoneNumber"
                                id="businessPhoneNumber"
                                placeholder="Wprowadź numer telefonu domowego"
                                maxLength={9}
                                minLength={9}
                                defaultValue={contactForAction ? contactForAction.businessPhoneNumber : ""}
                            />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label for="email">Email</Form.Label>
                            <Form.Control
                                required
                                type="email"
                                name="email"
                                id="email"
                                placeholder="Wprowadź email"
                                defaultValue={
                                    contactForAction ? contactForAction.email : ""
                                }
                            />
                        </Form.Group>

                    </Form>

                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Zamknij
                    </Button>
                    <Button variant="primary" type="submit" form="update-contact-form"
                    >Zapisz</Button>
                </Modal.Footer>
            </Modal>

        </>
        );
}

export default PhoneBookPage;