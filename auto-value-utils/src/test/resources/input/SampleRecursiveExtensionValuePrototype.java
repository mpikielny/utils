package com.slimgears.sample;

import com.slimgears.util.autovalue.annotations.UseBuilderExtension;
import com.slimgears.util.autovalue.annotations.UseJacksonAnnotator;
import com.slimgears.util.autovalue.annotations.UseMetaDataExtension;
import com.slimgears.util.autovalue.annotations.AutoValuePrototype;

import javax.annotation.Nullable;
import com.slimgears.util.sample.RecursiveCompositeAnnotation;

@RecursiveCompositeAnnotation
@UseMetaDataExtension
public interface SampleRecursiveExtensionValuePrototype {
    int intValue();
}
