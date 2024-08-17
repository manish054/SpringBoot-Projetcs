document.addEventListener("DOMContentLoaded", () => {
  console.log("viewContactProfile");

  const viewContactModal = document.getElementById("view_contact_profile");
  console.log(viewContactModal);

  if (!viewContactModal) {
    console.error("The modal element was not found");
    return;
  }

  // Initialize your modal options
  const options = {
    placement: "bottom-right",
    backdrop: "dynamic",
    backdropClasses: "bg-green-900/50 dark:bg-gray-900/80 fixed inset-0 -z-40",
    closable: true,
    onHide: () => {
      console.log("modal is hidden");
    },
    onShow: () => {
      console.log("modal is shown");
      setTimeout(() => {
        // Ensure `contactModal` is properly initialized before trying to use it
        if (contactModal && contactModal.classList) {
          contactModal.classList.add("scale-100");
        } else {
          console.error("contactModal is not properly initialized or has no classList property");
        }
      }, 50);
    },
    onToggle: () => {
      console.log("modal has been toggled");
    },
  };

  const instanceOptions = {
    id: "view_contact_profile_modal",
    override: true,
  };

  let contactModal;
  try {
    contactModal = new Modal(viewContactModal, options, instanceOptions);
  } catch (error) {
    console.error("Error initializing modal:", error);
    return;
  }
  console.log("contactModal---", contactModal);

  // Define global functions
  window.openViewContactProfile = () => {
    console.log("openViewContactProfile");
    if (contactModal) {
      contactModal.show();
      console.log("contactModal.show:", contactModal.show);
    } else {
      console.error("contactModal is not initialized");
    }
  };

  window.closeViewContactProfile = () => {
    if (contactModal) {
      contactModal.hide();
    } else {
      console.error("contactModal is not initialized");
    }
  };
});
