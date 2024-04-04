import tkinter as tk
from tkinter import ttk

class WaterTrackerApp:
    def __init__(self, master):
        self.master = master
        self.master.title("Sprout!")
        self.master.configure(bg="#333")
        self.master.minsize(900, 600)  # Set minimum window size

        self.water_intake = 0
        self.max_intake = 11  # Maximum intake per day in glasses

        self.master.grid_rowconfigure(0, weight=1)
        self.master.grid_rowconfigure(1, weight=1)
        self.master.grid_columnconfigure(0, weight=1)
        self.master.grid_columnconfigure(1, weight=1)

        # Title
        self.title_label = tk.Label(master, text="Sprout!", font=("Arial", 20), bg="#333", fg="white")
        self.title_label.grid(row=0, column=0, pady=(10,0), sticky="ew")

        self.water_gauge_frame = tk.Frame(master, bg="#333")
        self.water_gauge_frame.grid(row=1, column=0, padx=10, pady=10, sticky="ew")
        self.water_gauge_frame.grid_rowconfigure(0, weight=1)
        self.water_gauge_frame.grid_columnconfigure(0, weight=1)
        self.water_gauge_canvas = tk.Canvas(self.water_gauge_frame, bg="#333", highlightthickness=0)
        self.water_gauge_canvas.pack(side="bottom", pady=10)

        # Drink water entry
        self.drink_label = tk.Label(master, text="Enter how much water you drank (in glasses):", bg="#333", fg="white", font=("Arial", 12))
        self.drink_label.grid(row=2, column=0, pady=(10,5), sticky="w")
        self.drink_frame = tk.Frame(master, bg="#333")
        self.drink_frame.grid(row=3, column=0, pady=(0,10), sticky="ew")
        self.drink_frame.grid_columnconfigure(0, weight=1)
        self.drink_entry = tk.Entry(self.drink_frame, font=("Arial", 12))
        self.drink_entry.pack(side=tk.LEFT, padx=5)
        self.drink_button = tk.Button(self.drink_frame, text="Drink", command=self.drink_water, font=("Arial", 12))
        self.drink_button.pack(side=tk.LEFT, padx=5)

        # Plant image
        self.plant_images = [
            tk.PhotoImage(file="placeholder-image.png"),
            tk.PhotoImage(file="placeholder-image.png"),
            tk.PhotoImage(file="placeholder-image.png")
        ]
        self.plant_label = tk.Label(master, image=self.plant_images[0], bg="#333")
        self.plant_label.grid(row=1, column=1, padx=(0,10), pady=10, rowspan=3, sticky="nsew")

    def drink_water(self):
        try:
            intake = int(self.drink_entry.get())
            self.water_intake += intake
            if self.water_intake > self.max_intake:
                self.water_intake = self.max_intake
            self.update_gauge()
            self.update_plant()
        except ValueError:
            print("Please enter a valid number.")

    def update_gauge(self):
        self.water_gauge_canvas.delete("all")
        percentage = (self.water_intake / self.max_intake) * 100
        self.water_gauge_canvas.create_rectangle(5, 120, 45, 120 - percentage * 2, fill='blue', outline='white')

    def update_plant(self):
        if self.water_intake == 0:
            self.plant_label.config(image=self.plant_images[0])
        elif self.water_intake <= self.max_intake / 2:
            self.plant_label.config(image=self.plant_images[1])
        else:
            self.plant_label.config(image=self.plant_images[2])


if __name__ == "__main__":
    root = tk.Tk()
    root.configure(bg="#333")
    app = WaterTrackerApp(root)
    root.mainloop()
