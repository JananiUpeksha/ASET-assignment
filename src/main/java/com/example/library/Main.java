package com.example.library;
import java.lang.reflect.Field;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Library model = new Library();
        LibraryView view = new LibraryView();
        LibraryController controller = new LibraryController(model, view);

        // Run the application
        controller.run();

        // Reflection Inspection (validated via output)
        System.out.println("\n=== MVC Reflection Inspection ===");
        inspectMvcStructure(controller);

        System.out.println("\n=== Annotated Entities ===");
        inspectAnnotations();
    }

    private static void inspectMvcStructure(LibraryController controller) {
        Class<?> clazz = controller.getClass();
        System.out.println("Controller class: " + clazz.getName());

        // Detect Model and View fields via reflection
        Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
            if (field.getType() == Library.class) {
                System.out.println("Found Model reference: " + field.getName());
            }
            if (field.getType() == LibraryView.class) {
                System.out.println("Found View reference: " + field.getName());
            }
        });

        // Check @MvcComponent on known classes
        for (Class<?> c : new Class<?>[]{Library.class, LibraryView.class, LibraryController.class}) {
            if (c.isAnnotationPresent(MvcComponent.class)) {
                MvcComponent ann = c.getAnnotation(MvcComponent.class);
                System.out.println(c.getSimpleName() + " is annotated as MVC " + ann.role());
            }
        }
    }

    private static void inspectAnnotations() {
        for (Class<?> c : new Class<?>[]{Book.class, Member.class}) {
            if (c.isAnnotationPresent(PersistentEntity.class)) {
                PersistentEntity ann = c.getAnnotation(PersistentEntity.class);
                System.out.println(c.getSimpleName() + ": " + ann.description());
            }
        }
    }
}