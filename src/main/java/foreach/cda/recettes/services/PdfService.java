package foreach.cda.recettes.services;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import foreach.cda.recettes.dtos.RecetteIngredientResponseDto;
import foreach.cda.recettes.dtos.RecettesResponseDto;

import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {

public byte[] generateRecettesPdf(RecettesResponseDto recette, List<RecetteIngredientResponseDto> ingredients) throws Exception {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, out);

    document.open();

    Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, Color.DARK_GRAY);
    Font fontLabel = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
    Font fontValue = FontFactory.getFont(FontFactory.HELVETICA, 12);

    Paragraph title = new Paragraph("Fiche Recette : " + recette.getNomPlat(), fontTitle);
    title.setAlignment(Element.ALIGN_CENTER);
    title.setSpacingAfter(30);
    document.add(title);

    document.add(createLabeledLine("Nom du plat : ", recette.getNomPlat(), fontLabel, fontValue));
    document.add(createLabeledLine("Temps de préparation : ", recette.getDureePreparation() + " min", fontLabel, fontValue));
    document.add(createLabeledLine("Temps de cuisson : ", recette.getDureeCuisson() + " min", fontLabel, fontValue));
    document.add(createLabeledLine("Calories : ", recette.getNombreCalorique() + " kcal", fontLabel, fontValue));

    // --- SECTION INGREDIENTS ---
    Paragraph ingredientsTitle = new Paragraph("Ingrédients :", fontLabel);
    ingredientsTitle.setSpacingBefore(20);
    ingredientsTitle.setSpacingAfter(10);
    document.add(ingredientsTitle);

    for (RecetteIngredientResponseDto ing : ingredients) {
    String line = "- " + ing.getQuantite() + " " + ing.getIngredientLibelle()
                  + " (" + ing.getPreparation() + ")";
    document.add(new Paragraph(line, fontValue));
}


    document.close();
    return out.toByteArray();
}

private Paragraph createLabeledLine(String label, String value, Font labelFont, Font valueFont) {
    Paragraph p = new Paragraph();
    p.add(new Chunk(label, labelFont));
    p.add(new Chunk(value != null ? value : "N/A", valueFont));
    p.setSpacingAfter(10f); 
    return p;
}
}